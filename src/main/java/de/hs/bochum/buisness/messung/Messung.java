package de.hs.bochum.buisness.messung;



import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Messung {
	
	private int id;
	private int laufendeNummer;
	private double wert;

	public String gibAttributeAus(){
		return this.laufendeNummer + ": " + this.wert;
	}
}
