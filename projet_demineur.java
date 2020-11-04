// Ceci est un squelette à REMPLIR pour le projet INF1 sur le jeu de démineur
//
// - N'oubliez pas de renseigner vos deux noms
// Prénom Nom Groupe : élève 1/2
// Prénom Nom Groupe élève 2/2
//
// - Pour chaque question, le squelette donne le nom de la fonction à écrire mais *pas* la signature
//   il faut remplir les types d'entrées et de sorties (indiqués par ?) et remplir l'intérieur du code de chaque fonction.
//
// - L'unique fichier de code que vous soumettrez sera ce fichier Java, donc n'hésitez pas à le commenter abondamment.
//   inutile d'exporter votre projet comme archive Zip et de rendre ce zip.
//   Optionnel : vous pouvez aussi joindre un document PDF donnant des explications supplémentaires (si vous utilisez OpenOffice/LibreOffice/Word, exportez le document en PDF), avec éventuellement des captures d'écran montrant des étapes affichées dans la console
//
// - Regardez en ligne sur le Moodle pour le reste des consignes, et dans le fichier PDF du sujet du projet
//   https://foad.univ-rennes1.fr/mod/assign/view.php?id=534254
//
// - A rendre avant le vendredi 04 décembre, maximum 23h59.
//
// - ATTENTION Le projet est assez long, ne commencez pas au dernier moment !
//
// - Enfin, n'hésitez pas à contacter l'équipe pédagogique, en posant une question sur le forum du Moodle, si quelque chose n'est pas clair.
//

// Pour utiliser des scanners pour lire des entrées depuis le clavier
// utilisés en questions 4.d] pour la fonction jeu()
import java.util.Scanner;
//Projet demineur INF1 de Axel HOMERY et Fabien GUIHARD - groupe 6B

// Pour la fonction entierAleatoire(a, b) que l'on vous donne ci-dessous
import java.util.concurrent.ThreadLocalRandom;

// L'unique classe de votre projet
public class projet_demineur {

	// Donné, utile pour la question 1.b]
	public static int entierAleatoire(int a, int b){
		// Renvoie un entier aléatoire uniforme entre a (inclus) et b (inclus).
		return ThreadLocalRandom.current().nextInt(a, b + 1);
	}


	//
	// Exercice 1 : Initialisation des tableaux
	//

	// Question 1.a] d�clarer les variables globales T et Tadj ici
	static int[][] T; //Voici les variables globale
	static int[][] Tadj;

	// Question 1.b] Fonction init
	public static void init(int h, int l, int n) { 
		
		T = new int[h][l];
		Tadj = new int[h][l];
		int x = 0;
		int y = 0;
		
		for(int mine = 0; mine < n; mine++) {  //Place n bombes aleatoirement dans le tableau Tadj
			x = entierAleatoire(0, Tadj.length-1);
			y = entierAleatoire(0, Tadj[0].length-1); // (x, y) -> position al�atoire

			while(Tadj[x][y] != -1) {
				Tadj[x][y] = -1;
			}
		}
		
		for(int i = 0; i < T.length; i++) { //just for printing, les cases avec -1 contiennent une bombe, 0 ne contient pas de bombes
			for(int j = 0; j < T[i].length; j++) {
				System.out.print(Tadj[i][j] + " ");
			}System.out.println();
		}
	}

	// Question 1.c] Fonction caseCorrecte
	public static boolean caseCorrecte(int i, int j) { 
		if((T.length < i && i > 0) || (T[0].length < j && j > 0)) {
			return false;
		}
		return true;
	}

	// Question 1.d] Fonction calculerAdjacent
	public static void calculerAdjacent() {
		
		for(int i = 0; i < Tadj.length; i++) {   
			for(int j = 0; j < Tadj[i].length; j++) {  //the double for loop examines each element of the 2D array 
				if(Tadj[i][j] != -1) {                 //skipping if the element of the array is a bomb.
					
					for(int x = i-1; x <= i+1; x++) {       // examine les lignes autour de l'element du tableau
						for(int y = j-1; y <= j+1; y++) {  //examine les colonnes autour de l'element
							
							if(Tadj[x][y] == -1 && caseCorrecte(x, y)) {  //l'appel a la fonction evite de sortir du tableau
								Tadj[i][j]++;
							}
						}
					}
				}
			}
		}
	}
	
	//
	// Exercice 2 : Affichage de la grille
	//

	// Question 2.a]
	public static void afficherGrille(boolean affMines) { // ATTENTION, vous devez modifier la signature de cette fonction

		// Note : Dans un premier temps, considérer que la grille contiendra au plus 52 colonnes
		// (une pour chaque lettre de l'alphabet en majuscule et minuscule) et au plus 100 lignes
		// (entiers de 0 à 99).
		

			System.out.print("  |"); // Case vide en haut � gauche

			for(char i = 'A'; i < T[0].length + 'A' && i <= 'Z'; i++) { // Ligne des lettres MAJUSCULES
				System.out.print(i + "|");
			}
			for(char i = 'a'; i < T[0].length + 'A' + 6 && i <= 'z'; i++) { // Ligne des lettres minuscules
				System.out.print(i + "|");
			}
			System.out.println();

			for(int i = 0; i < T.length; i++) {
				if(i < 10) System.out.print("0"); // Affichage des nombres sur le c�t�
				System.out.print(i + "|");

				for(int j = 0; j < T[i].length; j++) { // Affichage cases :

					if(affMines == true && Tadj[i][j] == -1) { // Si mine et affMine true : "X"
						System.out.print("X");
					}
					else {
						if(T[i][j] == 0) System.out.print(" "); // non r�v�l�e
						else if(T[i][j] == 1) System.out.print(Tadj[i][j]); // R�v�l�e
						else System.out.print("!"); // Drapeau
					}
					System.out.print("|");
				}
				System.out.println();
			}
	}


	//
	// Exercice 3 : Révéler une case
	//

	// Question 3.a]
	public static void caseAdjacenteZero() { // ATTENTION, vous devez modifier la signature de cette fonction
		
		
	}

	// Question 3.b]
	public static void revelation() { // ATTENTION, vous devez modifier la signature de cette fonction
		

	}


	// Question 3.c] Optionnel
	public static void relevation2() { // ATTENTION, vous devez modifier la signature de cette fonction
		
	
	}

	// Question 3.d]
	public static void actionDrapeau() { // ATTENTION, vous devez modifier la signature de cette fonction
		
	}
	
	
	// Question 3.e]
	public static void revelerCase() { // ATTENTION, vous devez modifier la signature de cette fonction
		
	}


	//
	// Exercice 4 : Boucle de jeu
	//

	// Question 4.a]
	public static void aGagne() {
		
	}

	// Question 4.b]
	public static void verifierFormat() { // ATTENTION, vous devez modifier la signature de cette fonction
		
		
	}

	// Question 4.c]
	public static void conversionCoordonnees() { // ATTENTION, vous devez modifier la signature de cette fonction
		
		
	}

	// Question 4.d]
	public static void jeu() {
		
	}

	// Question 4.e]
	// Votre *unique* méthode main
	public static void main(String[] args) {
		init(8,52,2);
		afficherGrille(false);
		

	}


	//
	// Exercice 5 bonus challenge : Pour aller plus loin
	//

	// Question 5.a] Optionnel
	public static void aide() {
		
	}

	// Question 5.b] Optionnel
	public static void intelligenceArtificielle() {
		
	}

	// Question 5.c] Optionnel
	public static void statistiquesVictoiresIA() {
		
	}

}
