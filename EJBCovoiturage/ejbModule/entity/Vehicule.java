package entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Vehicule {

	
		@Id	
		private String typeVehicule;

		public String getTypeVehicule() {
			return typeVehicule;
		}

		public void setTypeVehicule(String typeVehicule) {
			this.typeVehicule = typeVehicule;
		}

		
		
}
