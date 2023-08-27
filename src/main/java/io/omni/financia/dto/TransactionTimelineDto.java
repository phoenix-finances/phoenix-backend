package io.omni.financia.dto;

import io.omni.financia.domains.AppUser;
import io.omni.financia.domains.TransactionTimeline;
import lombok.Data;

@Data
public class TransactionTimelineDto {
    private Long id;
    private AppUser owner;

    public TransactionTimeline toEntity() {
        TransactionTimeline entity = new TransactionTimeline();
        entity.setOwner(new AppUser(owner.getId()));
        entity.setId(id);
        return entity;
    }


    public static TransactionTimelineDto from(TransactionTimeline entity) {
        TransactionTimelineDto dto = new TransactionTimelineDto();
        dto.setId(entity.getId());
        dto.setOwner(entity.getOwner());
        return dto;
    }


}
