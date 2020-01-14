package org.enterpriseflowsrepository.api.traces.quarkus.converters;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.ClientErrorException;

import org.enterpriseflowsrepository.api.traces.quarkus.beans.Message;
import org.enterpriseflowsrepository.api.traces.quarkus.model.MessageModel;

@ApplicationScoped
public class MessageConverter extends AbstractConverter<MessageModel, Message> {

    @Inject
    KeyConverter keyConverter;

    @Override
    public Message toDto(MessageModel model) throws ClientErrorException {
        if (model == null) {
            return null;
        }

        Message dto = new Message();

        dto.setBody(model.getBody());
        dto.setCorrelationID(model.getCorrelationID());
        dto.setCreated(model.getCreated());
        dto.getHeaders().addAll(keyConverter.toDto(model.getHeaders()));
        dto.setLevel(Message.Level.fromValue(model.getLevel().value()));
        dto.setMessageID(model.getMessageID());
        dto.setType(Message.Type.fromValue(model.getType().value()));

        return dto;
    }

    @Override
    public Message toDtoWithLinks(MessageModel model) {
        Message dto = toDto(model);

        // nothing needed here

        return dto;
    }

    @Override
    public MessageModel toModel(Message dto) {
        if (dto == null) {
            return null;
        }

        MessageModel model = new MessageModel();

        model.setBody(dto.getBody());
        model.setCorrelationID(dto.getCorrelationID());
        model.setCreated(dto.getCreated());
        model.getHeaders().addAll(keyConverter.toModel(dto.getHeaders()));
        model.setLevel(MessageModel.Level.fromValue(dto.getLevel().value()));
        model.setMessageID(dto.getMessageID());
        model.setType(MessageModel.Type.fromValue(dto.getType().value()));

        return model;
    }
}
