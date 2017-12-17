package entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Tarif {

	
		
		@Id	@GeneratedValue
		private int id;
		
		private Float valeur;

		public Tarif() {	
		}
		
		public Tarif(Float val) {
			this.valeur=val;
		}
		
		public int getId() {
			return id;
		}

		public void setId(int id) {
			this.id = id;
		}

		public Float getValeur() {
			return valeur;
		}

		public void setValeur(Float valeur) {
			this.valeur = valeur;
		}

		

		
		
}