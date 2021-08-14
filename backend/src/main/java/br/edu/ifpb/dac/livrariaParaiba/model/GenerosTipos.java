package br.edu.ifpb.dac.livrariaParaiba.model;

/**
 * Enum que cataloga os generos disponiveis na livria
 * 
 * @author bruno
 */

public enum GenerosTipos {
	ROMANCE {
		public String toString() {
			return "ROMANCE";
		}
	},
	COMEDIA {
		public String toString() {
			return "COMEDIA";
		}
	},
	FICÇÃO_CIENTIFICA {
		public String toString() {
			return "FICÇÃO_CIENTIFICA";
		}
	},
	DRAMA {
		public String toString() {
			return "DRAMA";
		}
	},
	HQ {
		public String toString() {
			return "HQ";
		}
	},
	INFANTIL {
		public String toString() {
			return "INFANTIL";
		}
	},
	TERROR {
		public String toString() {
			return "TERROR";
		}
	},
	CONTO {
		public String toString() {
			return "CONTO";
		}
	},
	CRÔNICA {
		public String toString() {
			return "CRÔNICA";
		}
	},
	POESIA {
		public String toString() {
			return "POESIA";
		}
	}
}
