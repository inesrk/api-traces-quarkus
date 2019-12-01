#!/bin/bash

##
## This script aims to push the previously built container to its repository. By default, it tag the build with the full tag ($IMAGE_TAG_FULL, i.e. '1.7.2-20191113211021-921103d') and 'latest'. If the argument 'stable' is specified, it also pushes with the short version tag ($IMAGE_TAG_SHORT, i.e. '1.7.2') and the shorter one ($CONTAINER_TAG_SHORTER, i.e. '1.7'), usually applied as versions selectors in a daily usage & non-dev environments (i.e. 'enterpriseflowsrepository/dashboard:1.7.2' or 'enterpriseflowsrepository/dashboard:1.7').
##

set -e

usage() {
    echo "➡ Usage: $0 -r 'URL' -u 'USERNAME' -p 'PASSWORD' -a 'ACTION' -i 'org/name' -v '1.0.1' -w '1.0.1-descriptor'"
    echo '  -r URL          : Repository URL'
    echo '  -u string       : Repository username'
    echo '  -p string       : Repository password'
    echo '  -a enum         : Set of actions to perform (can be: default, stable)'
    echo '  -i [org]/[name] : Name of the local image to push'
    echo '  -v SmVer        : Small fersion tag for this image (i.e. "1.7.2")'
    echo '  -w string       : Full version tag full for this image (i.e. "1.7.2-20191113211021-921103d")'
}

while getopts 'r:u:p:a:i:v:w:' OPTION; do
    case "$OPTION" in
        # Container repository link
        # URL
        r)
            REPO_URL=${OPTARG};;
        # Container repository username
        # string
        u)
            REPO_USERNAME=${OPTARG};;
        # Container repository password
        # string
        p)
            REPO_PASSWORD=${OPTARG};;
        # Action type.
        # Enum: default, stable
        a)
            ACTION_TYPE=${OPTARG};;
        # Name of the local image to push.
        # Pattern: [org]/[name]
        i)
            IMAGE_NAME=${OPTARG};;
        # Small fersion tag for this image (i.e. "1.7.2")
        # SmVer
        v)
            IMAGE_TAG_SHORT=${OPTARG};;
        # Full version tag full for this image (i.e. "1.7.2-20191113211021-921103d")
        # string
        w)
            IMAGE_TAG_FULL=${OPTARG};;
        \?)
            echo "🤷‍♀️ Unknown option: -$OPTARG" >&2
            usage
            exit 1
            ;;
        :)
            echo "👀 Missing option argument for -$OPTARG" >&2
            usage
            exit 1
            ;;
        *)
            echo "⁉ Unimplemented option: -$OPTARG" >&2
            usage
            exit 1
            ;;
    esac
done

if [[ -z "$REPO_URL" || -z "$REPO_USERNAME" || -z "$REPO_PASSWORD" || -z "$ACTION_TYPE" || -z "$IMAGE_NAME" || -z "$IMAGE_TAG_SHORT" || -z "$IMAGE_TAG_FULL" ]]; then
    if [ -z "$REPO_URL" ]; then
        echo "⚠ REPO_URL is unset or set to the empty string" >&2
    fi
    if [ -z "$REPO_USERNAME" ]; then
        echo "⚠ REPO_USERNAME is unset or set to the empty string" >&2
    fi
    if [ -z "$REPO_PASSWORD" ]; then
        echo "⚠ REPO_PASSWORD is unset or set to the empty string" >&2
    fi
    if [ -z "$ACTION_TYPE" ]; then
        echo "⚠ ACTION_TYPE is unset or set to the empty string" >&2
    fi
    if [ -z "$IMAGE_NAME" ]; then
        echo "⚠ IMAGE_NAME is unset or set to the empty string" >&2
    fi
    if [ -z "$IMAGE_TAG_SHORT" ]; then
        echo "⚠ IMAGE_TAG_SHORT is unset or set to the empty string" >&2
    fi
    if [ -z "$IMAGE_TAG_FULL" ]; then
        echo "⚠ IMAGE_TAG_FULL is unset or set to the empty string" >&2
    fi

    usage
    exit 1
fi

## Push container with tags latest and IMAGE_TAG_FULL
push_default() {
    echo -e "\n\033[1m🌠 Pushing the build '$IMAGE_NAME' (tags: 'latest', '$IMAGE_TAG_FULL') to the container repository...\033[0m"

    # Define tags
    echo \
        "$IMAGE_NAME:latest" \
        "$IMAGE_NAME:$IMAGE_TAG_FULL" \
            | xargs -n 1 docker tag $IMAGE_NAME

    # Push the image
    echo \
        "$IMAGE_NAME:latest" \
        "$IMAGE_NAME:$IMAGE_TAG_FULL" \
            | xargs -n 1 docker push
}

## Push container with tag IMAGE_TAG_SHORT
push_stable() {
    echo -e "\n\033[1m🌠 Pushing the build '$IMAGE_NAME' (tags: '$IMAGE_TAG_SHORT') to the container repository...\033[0m"

    # Define tags
    echo \
        "$IMAGE_NAME:$IMAGE_TAG_SHORT" \
            | xargs -n 1 docker tag $IMAGE_NAME

    # Push the image
    echo \
        "$IMAGE_NAME:$IMAGE_TAG_SHORT" \
            | xargs -n 1 docker push
}

main() {
    echo -e "\n\033[1m🔑 Login to the container repository '$REPO_URL' with the username '$REPO_USERNAME'...\033[0m"

    echo "$REPO_PASSWORD" | docker login --username "$REPO_USERNAME" --password-stdin "$REPO_URL"

    if [[ $ACTION_TYPE == 'default' ]]; then
        push_default
    else
        echo 'Abort "default" push, option not provided'
    fi

    if [[ $ACTION_TYPE == 'stable' ]]; then
        push_stable
    else
        echo 'Abort "stable" push, option not provided'
    fi
}

main
