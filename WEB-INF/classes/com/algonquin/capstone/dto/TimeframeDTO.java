package com.algonquin.capstone.dto;


public class TimeframeDTO {
	
	private String TimeFrameId;
	private String TFUUID;
	private String OwnerId;
	private String LocationId;
	
	private String StartDate;
	private String EndDate;
	private String Increment;
	private String Name;
	private String Description;
	private String oneSlot;
	private String IsPrivate;
	private String AccessPin;
	private String CreatedBy;
	private String CreatedOn;
	private String UpdatedBy;
	private String UpdatedOn;
	private String DeletedBy;
	private String DeletedOn;
	
	public TimeframeDTO() {
		
	}
	
	public String getTimeFrameId() {
		return TimeFrameId;
	}
	public void setTimeFrameId(String timeFrameId) {
		TimeFrameId = timeFrameId;
	}
	public String getTFUUID() {
		return TFUUID;
	}
	public void setTFUUID(String tFUUID) {
		TFUUID = tFUUID;
	}
	public String getOwnerId() {
		return OwnerId;
	}
	public void setOwnerId(String ownerId) {
		OwnerId = ownerId;
	}
	public String getLocationId() {
		return LocationId;
	}
	public void setLocationId(String locationId) {
		LocationId = locationId;
	}
	public String getStartDate() {
		return StartDate;
	}
	public void setStartDate(String startDate) {
		StartDate = startDate;
	}
	public String getEndDate() {
		return EndDate;
	}
	public void setEndDate(String endDate) {
		EndDate = endDate;
	}
	public String getIncrement() {
		return Increment;
	}
	public void setIncrement(String increment) {
		Increment = increment;
	}
	public String getName() {
		return Name;
	}
	public void setName(String name) {
		Name = name;
	}
	public String getDescription() {
		return Description;
	}
	public void setDescription(String description) {
		Description = description;
	}
	public String getOneSlot() {
		return oneSlot;
	}
	public void setOneSlot(String oneSlot) {
		this.oneSlot = oneSlot;
	}
	public String getIsPrivate() {
		return IsPrivate;
	}
	public void setIsPrivate(String isPrivate) {
		IsPrivate = isPrivate;
	}
	public String getAccessPin() {
		return AccessPin;
	}
	public void setAccessPin(String accessPin) {
		AccessPin = accessPin;
	}
	public String getCreatedBy() {
		return CreatedBy;
	}
	public void setCreatedBy(String createdBy) {
		CreatedBy = createdBy;
	}
	public String getCreatedOn() {
		return CreatedOn;
	}
	public void setCreatedOn(String createdOn) {
		CreatedOn = createdOn;
	}
	public String getUpdatedBy() {
		return UpdatedBy;
	}
	public void setUpdatedBy(String updatedBy) {
		UpdatedBy = updatedBy;
	}
	public String getUpdatedOn() {
		return UpdatedOn;
	}
	public void setUpdatedOn(String updatedOn) {
		UpdatedOn = updatedOn;
	}
	public String getDeletedBy() {
		return DeletedBy;
	}
	public void setDeletedBy(String deletedBy) {
		DeletedBy = deletedBy;
	}
	public String getDeletedOn() {
		return DeletedOn;
	}
	public void setDeletedOn(String deletedOn) {
		DeletedOn = deletedOn;
	}
}
