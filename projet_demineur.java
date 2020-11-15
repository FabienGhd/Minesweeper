// Ceci est un squelette √† REMPLIR pour le projet INF1 sur le jeu de d√©mineur
//
// - N'oubliez pas de renseigner vos deux noms
// Pr√©nom Nom Groupe : √©l√®ve 1/2
// Pr√©nom Nom Groupe √©l√®ve 2/2
//
// - Pour chaque question, le squelette donne le nom de la fonction √† √©crire mais *pas* la signature
//   il faut remplir les types d'entr√©es et de sorties (indiqu√©s par ?) et remplir l'int√©rieur du code de chaque fonction.
//
// - L'unique fichier de code que vous soumettrez sera ce fichier Java, donc n'h√©sitez pas √† le commenter abondamment.
//   inutile d'exporter votre projet comme archive Zip et de rendre ce zip.
//   Optionnel : vous pouvez aussi joindre un document PDF donnant des explications suppl√©mentaires (si vous utilisez OpenOffice/LibreOffice/Word, exportez le document en PDF), avec √©ventuellement des captures d'√©cran montrant des √©tapes affich√©es dans la console
//
// - Regardez en ligne sur le Moodle pour le reste des consignes, et dans le fichier PDF du sujet du projet
//   https://foad.univ-rennes1.fr/mod/assign/view.php?id=534254
//
// - A rendre avant le vendredi 04 d√©cembre, maximum 23h59.
//
// - ATTENTION Le projet est assez long, ne commencez pas au dernier moment !
//
// - Enfin, n'h√©sitez pas √† contacter l'√©quipe p√©dagogique, en posant une question sur le forum du Moodle, si quelque chose n'est pas clair.
//

// Pour utiliser des scanners pour lire des entr√©es depuis le clavier
// utilis√©s en questions 4.d] pour la fonction jeu()
import java.util.Scanner;
//Projet demineur INF1 de Axel HOMERY et Fabien GUIHARD - groupe 6B

// Pour la fonction entierAleatoire(a, b) que l'on vous donne ci-dessous
import java.util.concurrent.ThreadLocalRandom;

// L'unique classe de votre projet
public class projet_demineur {

	// Donn√©, utile pour la question 1.b]
	public static int entierAleatoire(int a, int b){
		// Renvoie un entier al√©atoire uniforme entre a (inclus) et b (inclus).
		return ThreadLocalRandom.current().nextInt(a, b + 1);
	}


	//
	// Exercice 1 : Initialisation des tableaux
	//

	// Question 1.a] dÈclarer les variables globales T et Tadj ici
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
			y = entierAleatoire(0, Tadj[0].length-1); // (x, y) -> position alÈatoire

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

		// Note : Dans un premier temps, consid√©rer que la grille contiendra au plus 52 colonnes
		// (une pour chaque lettre de l'alphabet en majuscule et minuscule) et au plus 100 lignes
		// (entiers de 0 √† 99).
		

			System.out.print("  |"); // Case vide en haut ‡ gauche

			for(char i = 'A'; i < T[0].length + 'A' && i <= 'Z'; i++) { // Ligne des lettres MAJUSCULES
				System.out.print(i + "|");
			}
			for(char i = 'a'; i < T[0].length + 'A' + 6 && i <= 'z'; i++) { // Ligne des lettres minuscules
				System.out.print(i + "|");
			}
			System.out.println();

			for(int i = 0; i < T.length; i++) {
				if(i < 10) System.out.print("0"); // Affichage des nombres sur le cÙtÈ
				System.out.print(i + "|");

				for(int j = 0; j < T[i].length; j++) { // Affichage cases :

					if(affMines == true && Tadj[i][j] == -1) { // Si mine et affMine true : "!"
						System.out.print("!");
					}
					else {
						if(T[i][j] == 0) System.out.print(" "); // non rÈvÈlÈe
						else if(T[i][j] == 1) System.out.print(Tadj[i][j]); // RÈvÈlÈe
						else System.out.print("X"); // Drapeau
					}
					System.out.print("|");
				}
				System.out.println();
			}
	}


	//
	// Exercice 3 : R√©v√©ler une case
	//

	// Question 3.a]
	public static boolean caseAdjacenteZero(int i, int j) { //on considËre i et j valides
		
		for(int x = i-1; x <= i+1; x++) {
			for(int y = j-1; j <= j+1; y++) {
				if(caseCorrecte(x,y) && T[x][y] == 1 && Tadj[x][y] == 0) { //vÈrifie si une case adjacente par rapport ‡ la position (i,j) est rÈvÈlÈe et si la case n'a pas de mine adjacentes
					return true;
				}
			}
		}
		return false;
	
	}

	// Question 3.b]
	public static void revelation(int i, int j) { //on considËre i et j valides 
		
		T[i][j] = 1; // case rÈvÈlÈe
		
		if(Tadj[i][j] == 0) { // la case n'a (i,j) n'a aucune mine adjacente
			for(int x = i-1; x <= i+1; x++) { 
			for(int y = j-1; j <= j+1; y++) {
				if(Tadj[x][y] == 0 && T[x][y] == 0 && caseCorrecte(x, y)) {  
					T[x][y] = 1; //rÈvËle case adjacente ‡ (i, j) qui n'ont pas de mine adjacente
				}
			}
		}
		}
	}


	// Question 3.c] Optionnel
	public static void relevation2() { // ATTENTION, vous devez modifier la signature de cette fonction
		
	
	}

	// Question 3.d]
	public static void actionDrapeau(int i, int j) { 
		if(!caseCorrecte(i, j)) {
			System.out.println("Les coordonnÈes rentrÈes en paramËtre de la fonction 'actionDrapeau' ne sont pas valides !");
		}
		if(T[i][j] != 1) { //case (i,j) -> non rÈvÈlÈ (l'Ètat de la case peut Ítre 0 ou 2)
			if(T[i][j] == 2) { //si la case est marquÈ par un drapeau
				T[i][j] = 0;   //on enlËve ce-dernier
			} else {
				T[i][j] = 2; //dans le cas o˘ l'Ètat de la case est 0, on met un drapeau
			}
		}
	}
	
	
	// Question 3.e]
	public static boolean revelerCase(int i, int j) { 
		if(Tadj[i][j] == -1) {
			return false;
		} else {
			revelation(i,j);
			return true;
		}
		
		
	}


	//
	// Exercice 4 : Boucle de jeu
	//

	// Question 4.a]
	public static boolean aGagne() {
		int mine = 0;
		int cpt = 0;
		//comptons le nombre de mines dans une grille :
		
		for(int i = 0; i < T.length; i++) {
			for(int j = 0; j < T[i].length; j++) {
				if(Tadj[i][j] == -1) {
					mine++;
				}
			}
		}
			//nous connaisons maintenant le nombre de mines, qui est contenu dans la variable 'mine'
			//Assurons-nous dËs ‡ prÈsent que toutes les cases sont rÈvÈlÈ hormis les cases contenant des mines : 
			
			for(int i = 0; i < T.length; i++) {
				for(int j = 0; j < T[i].length; j++) {
					if((T[i][j] == 2 && Tadj[i][j] == -1) ||   //la case est marquÈ par un drapeau et est bien une mine
					   (T[i][j] == 1 && Tadj[i][j] != -1)) {   //la case est rÈvÈlÈe est n'est pas une mine
						cpt++;
					}
				}
			} return cpt == (T.length*T[0].length) - mine;
		}
	

	// Question 4.b]
	public static boolean verifierFormat(String s) { // ATTENTION, vous devez modifier la signature de cette fonction
		if(s.length() != 4) {
			return false;
		} else {
			if(s.charAt(0) != 'r' && s.charAt(0) != 'd') {
				return false;
			}
		}
		
		
	}

	// Question 4.c]
	public static void conversionCoordonnees() { // ATTENTION, vous devez modifier la signature de cette fonction
		
		
	}

	// Question 4.d]
	public static void jeu() {
		
	}

	// Question 4.e]
	// Votre *unique* m√©thode main
	public static void main(String[] args) {
		init(10,10,2);
		afficherGrille(true);
		

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
