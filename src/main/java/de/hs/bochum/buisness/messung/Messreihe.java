package de.hs.bochum.buisness.messung;

import java.util.List;
import java.util.Set;



import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@EqualsAndHashCode

@AllArgsConstructor
public class Messreihe {
	
	private int messreihenId;
	private int zeitintervall;
	private String verbraucher;
	private String messgroesse;
	private List<Messung> elements;
	
	
	public String gibAttributeAus(){
		return this.messreihenId + " " 
	 	    + this.zeitintervall + " " + this.verbraucher + " " + this.messgroesse;
	}

}
