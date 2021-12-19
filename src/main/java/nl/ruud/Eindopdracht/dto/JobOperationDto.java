package nl.ruud.Eindopdracht.dto;

import nl.ruud.Eindopdracht.model.JobOperation;
import nl.ruud.Eindopdracht.model.JobOperationID;

public class JobOperationDto {

    private JobOperationID ID;

    private String description;




    public static JobOperationDto fromJobOperation(JobOperation jobOperation){
        JobOperationDto Dto = new JobOperationDto();
        Dto.setID(jobOperation.getID());
        Dto.setDescription(jobOperation.getOperation().getDescription());
        return Dto;
    }



    public JobOperationID getID() {
        return ID;
    }

    public void setID(JobOperationID ID) {
        this.ID = ID;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
