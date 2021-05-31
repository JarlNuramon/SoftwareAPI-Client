package de.hs.bochum.buisness.messung;

import java.util.List;

import lombok.Data;

@Data
public class MessreiheResponse {
	private List<Messreihe> messungen;
}
