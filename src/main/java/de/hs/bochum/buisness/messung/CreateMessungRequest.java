package de.hs.bochum.buisness.messung;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CreateMessungRequest {

	private int zeitIntervall;
	private String verbraucher;
	private String messgroesse;
	private int id;
}
