package entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

@Entity
public class Vehicule {

	
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
		private int id;
		
		private String gabaritVehicule;
		
		public Vehicule() {
			
		}

		public Vehicule(String vehicule) {
			this.gabaritVehicule=vehicule;
		}
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
