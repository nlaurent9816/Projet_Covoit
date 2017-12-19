package entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

@Entity @SequenceGenerator(name = "port_gen", sequenceName = "port_gen",  initialValue = 1000)
public class Vehicule {

	
	@Id @GeneratedValue(strategy = GenerationType.AUTO, generator = "port_gen")
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
