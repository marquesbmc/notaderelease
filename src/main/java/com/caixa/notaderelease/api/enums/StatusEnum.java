package com.caixa.notaderelease.api.enums;

public enum StatusEnum {
	
	New,
	Assigned,
	Resolved,
	Approved,
	Disapproved,
	Close,
	Analise,
	Aprovado,
	Pendenciado,
	Designado, 
	Closed;
	
	
	public static StatusEnum getStatus(String status) {
		switch(status) {
			case "New" : return New;
			case "Assigned" : return Assigned;
			case "Resolved" : return Resolved;
			case "Approved" : return Approved;
			case "Disapproved" : return Disapproved;
			case "Close" : return Close;
			case "Analise" : return Analise;
			case "Aprovado" : return Aprovado;
			case "Pendenciado" : return Pendenciado;
			case "Designado" : return Designado;	
			case "Closed" : return Closed;	
		default : return New;
		
		
		
		}
		
		
	}
	

}
