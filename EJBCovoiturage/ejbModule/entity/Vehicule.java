package entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Vehicule {

	
		@Id	@GeneratedValue
		private int id;
		
		private String gabaritVehicule;

		public int getId() {
			return id;
		}

		public void setId(int id) {
			this.id = id;
		}

		public String getGabaritVehicule() {
			return gabaritVehicule;
		}

		public void setGabaritVehicule(String gabaritVehicule) {
			this.gabaritVehicule = gabaritVehicule;
		}
		
		
}
