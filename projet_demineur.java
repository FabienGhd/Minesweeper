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

	// Question 1.a] d?clarer les variables globales T et Tadj ici
	static int[][] T; //Voici les variables globale
	static int[][] Tadj;

	// Question 1.b] Fonction init
	public static void init(int h, int l, int n) { 
		
		T = new int[h][l];
		Tadj = new int[h][l];
		int x = entierAleatoire(0, Tadj.length-1);
		int y = entierAleatoire(0, Tadj[0].length-1);
		
		for(int mine = 0; mine < n; mine++) {  //Place n bombes aleatoirement dans le tableau Tadj
			while(Tadj[x][y] == -1) { // Tant que la pos tirée est une bombe, on tire à nouveau un pos au hasard
				x = entierAleatoire(0, Tadj.length-1);
				y = entierAleatoire(0, Tadj[0].length-1);
			}
			Tadj[x][y] = -1;
		}
	}

	// Question 1.c] Fonction caseCorrecte
	public static boolean caseCorrecte(int i, int j) { 
		if((i >= 0 && i < T.length) && (j >= 0 && j < T[0].length)) {
			return true;
		}
		return false;
	}

	// Question 1.d] Fonction calculerAdjacent
	public static void calculerAdjacent() {
		
		for(int i = 0; i < Tadj.length; i++) {   
			for(int j = 0; j < Tadj[i].length; j++) {  //parcours grille
				
				if(Tadj[i][j] != -1) {                 //passe si l'élement est une bombe.
					
					for(int x = i-1; x <= i+1; x++) {       // examine les lignes autour de l'element du tableau
						for(int y = j-1; y <= j+1; y++) {  //examine les colonnes autour de l'element
							
							if(caseCorrecte(x, y) && Tadj[x][y] == -1) {  //l'appel a la fonction 'caseCorrcte' evite de sortir du tableau
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
		

			System.out.print("  |"); // Case vide en haut ? gauche

			for(char i = 'A'; i < T[0].length + 'A' && i <= 'Z'; i++) { // Ligne des lettres MAJUSCULES
				System.out.print(i + "|");
			}
			for(char i = 'a'; i < T[0].length + 'A' + 6 && i <= 'z'; i++) { // Ligne des lettres minuscules
				System.out.print(i + "|");
			}
			System.out.println();

			for(int i = 0; i < T.length; i++) {
				if(i < 10) System.out.print("0"); // Affichage des nombres sur le cote
				System.out.print(i + "|");

				for(int j = 0; j < T[i].length; j++) { // Affichage cases :

					if(affMines == true && Tadj[i][j] == -1) { // Si mine et affMine true : "!"
						System.out.print("!");
					}
					else {
						if(T[i][j] == 0) System.out.print(" "); // non revele
						else if(T[i][j] == 1) System.out.print(Tadj[i][j]); // Revele
						else System.out.print("X"); // Drapeau
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
	public static boolean caseAdjacenteZero(int i, int j) { //on consid?re i et j valides

		for(int x = i-1; x <= i+1; x++) {
			for(int y = j-1; y <= j+1; y++) {
				if(caseCorrecte(x,y) && T[x][y] == 1 && Tadj[x][y] == 0) { //v?rifie si une case adjacente par rapport ? la position (i,j) est r?v?l?e et si la case n'a pas de mine adjacentes
					return true;
				}
			}
		}
		return false;
	
	}

	// Question 3.b]
	public static void revelation(int i, int j) { //on considere i et j valides 
		
		boolean oneIsRevealed = true;
		T[i][j] = 1; // case revelée
		
		while(oneIsRevealed == true) {
			oneIsRevealed = false;
			if(Tadj[i][j] == 0) {
				for(int x = 0; x < T.length; x++) { // Parcours grille
					for(int y = 0; y < T[x].length; j++) {
						if(caseAdjacenteZero(x, y) && T[x][y] == 0) {
							T[x][y] = 1;
							oneIsRevealed = true;
						}
						else {
						}
					}
				}
			}
		}
	}
	//Le programme utilise revelation2 du fait de sa complexité plus optimisé.

	// Question 3.c] Optionnel
	public static void revelation2(int i, int j) { // Fonction récursive
        T[i][j] = 1;

        if(Tadj[i][j] == 0) {
            for(int x = i-1; x <= i+1; x++) {       // examine les cases collees
                for(int y = j-1; y <= j+1; y++) {
                    if(caseCorrecte(x, y) && T[x][y] == 0) { // Si case dans grille && non révélée (nécéssaire au bon fonctionnement de la récursivité)
                        revelation2(x, y);
                    }
                }
            }
        }
    }

	// Question 3.d]
	public static void actionDrapeau(int i, int j) { 
		if(!caseCorrecte(i, j)) {
			System.out.println("Les coordonnees rentrees en parametre de la fonction 'actionDrapeau' ne sont pas valides !");
		}
		else if(T[i][j] != 1) { //case (i,j) -> non r?v?l? (l'?tat de la case peut ?tre 0 ou 2)
			if(T[i][j] == 2) { //si la case est marqu? par un drapeau
				T[i][j] = 0;   //on enl?ve ce-dernier
			} else {
				T[i][j] = 2; //dans le cas o? l'?tat de la case est 0, on met un drapeau
			}
		}
	}
	
	
	// Question 3.e]
	public static boolean revelerCase(int i, int j) { 
		if(Tadj[i][j] == -1) {
			return false;
		} else {
			revelation2(i,j);
			return true;
		}
		
		
	}


	//
	// Exercice 4 : Boucle de jeu
	//

	// Question 4.a]
	public static boolean aGagne() {  //  TODO: modifier car joueur gagne quand grille remplie de bombe
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
		//Assurons-nous d?s ? pr?sent que toutes les cases sont r?v?l? hormis les cases contenant des mines : 
		
		for(int i = 0; i < T.length; i++) {
			for(int j = 0; j < T[i].length; j++) {
				/*if(((T[i][j] == 2 || T[i][j] == 0) && Tadj[i][j] == -1) ||  //la case est marqu? par un drapeau ou est révélé et est bien une mine
				   (T[i][j] == 1 && Tadj[i][j] != -1)) {   //la case est r?v?l?e est n'est pas une mine
					cpt++;
				}*/
				if(T[i][j] == 1) cpt++;
			}
		} 
		return cpt == (T.length*T[0].length) - mine;
	}
	

	// Question 4.b]
	public static boolean verifierFormat(String s) { // ATTENTION, vous devez modifier la signature de cette fonction
		if(s.length() != 4) {
			return false;
		}
		// Fonction aide
		else if(s.equals("aide")) {
			aide();
			return false;
		}
		else {
			if(s.charAt(0) != 'r' && s.charAt(0) != 'd') return false;
			
			//vérification chiffre indice 1 et 2
			int cpt = 0;
			for(int i = '0' ; i <= '9'; i++) {
				if(s.charAt(1) == i) cpt++;
				if(s.charAt(2) ==  i) cpt++;
			} if(cpt != 2) return false;
			
			//vérification lettre indice 3
			
			for(int i = 'A'; i <= 'Z'; i++) {
				if(s.charAt(3) == i) return true;
			}
			for(int i = 'a'; i <= 'z'; i++) {
				if(s.charAt(3) == i) return true;
			}						
		}
		return false;		
	}

	// Question 4.c]
	public static int[] conversionCoordonnees(String input) { // ATTENTION, vous devez modifier la signature de cette fonction
		int[] t = new int[3];
		
		// Fonction aide
	
		if(input.charAt(0) == 'r') t[2] = 1;
		else t[2] = 0;
		
		//Transformation du caractère lettre en chiffre pour la colonne :
		int cpt = 0;
		for(int i = 'A'; i <= 'Z'; i++) {
			if(input.charAt(3) == i) {
				t[1] = cpt;
			} cpt++;
		} 
		cpt = 26;
		for(int i = 'a'; i <= 'z'; i++) {
			if(input.charAt(3) == i) {
				t[1] = cpt;
			} cpt++;
		} 
		
		//Transformation du caractère correspondant à la ligne en son entier :
		t[0] = Integer.parseInt(input.substring(1,3)); 
		
		/* Autre méthode sans utiliser la méthode substring : 
		 
		String res = new String();
		for(int i = 1; i < 3; i++) {
			res += s.charAt(i);
		}
		t[0] = Integer.parseInt(res);
		
		*/
		
		return t;
		
		
	}
	

	// Question 4.d]
	public static void jeu() {
		boolean perdu = false;
		Scanner sc = new Scanner(System.in);
		String input;
		int[] coords;
		
		while(!aGagne() && !perdu) {
			afficherGrille(false);
			
			System.out.print("Veuillez entrer les coordonnees souhaitées  :  ");
			input = sc.nextLine();
			
			while(!verifierFormat(input)) {
				System.out.println("Le format des coordonnees que vous avez entré n'est pas bon (ou vous avez utilisé l'aide), veuillez recommencer"); //TODO : changer si besoin les phrases, les alinéas..
				System.out.print("Veuillez entrer les coordonnees souhaitées  :  ");
				input = sc.nextLine();
			}
			coords = conversionCoordonnees(input);
			
			if(coords[2] == 1) {  //  Si joueur veut reveler case
				if(!revelerCase(coords[0], coords[1])) { 	//  Si case choisie = bombe -> perdu
					perdu = true;
					afficherGrille(true);  					// Affiche grille avec bombes
				}
				else revelerCase(coords[0], coords[1]);		//  Si case pas bombe
			}
			else actionDrapeau(coords[0], coords[1]);		//  Si placer drapeau
			
		}
		sc.close();
		if(aGagne()) System.out.println("Gagné !");	// Si on est sorti du while car le joueur a gagné
		else System.out.println("Perdu...");
	}

	// Question 4.e]
	// Votre *unique* méthode main
	public static void main(String[] args) {	
	
		//4.e]
		boolean correct = false;
		Scanner sc = new Scanner(System.in);
		
		
		System.out.print("Veuillez entrer la hauteur de la grille de jeu : ");
		int hauteur = sc.nextInt(); // TODO : gestion d'erreur si valeur entrée n'est pas int
		//vérification de la hauteur rentrée :
		while(!correct) { //boucle while -> permet de réitérer la demande jusqu'à ce que les coordonnées soient correctes
		if(hauteur < 1 || hauteur > 100) {
			System.out.print("La hauteur rentrée n'est pas conforme ! Veuillez rentrée de nouveau la hauteur de la grille avec un entier compris entre 1 et 100 inclus :");
			hauteur = sc.nextInt();
		} else correct = true;
		}
		correct = false;
		
		
		System.out.print("Veuillez entrer la largeur de la grille de jeu : ");
		int largeur = sc.nextInt();
		//Vérification de la largeur rentrée
		while(!correct) {
		if(largeur < 1 || largeur > 52)	{
			System.out.print("La largeur rentrée n'est pas conforme ! Veuillez rentrée de nouveau la hauteur de la grille avec un entier compris entre 1 et 52 inclus :");
			largeur = sc.nextInt();
		} else correct = true;
		}
		correct = false;
		
		
		System.out.print("Veuillez entrer le nombre de mine(s) dans le jeu : "); 
			int mine = sc.nextInt();
			while(!correct) {
			if(mine < 1 || mine > hauteur*largeur-1) { // hauteur * largeur-1 car impossible de jouer si la grille est entièrement remplie de bombes
				System.out.print("Le nombre de mine(s) rentrée n'est pas conforme ! Veuillez entrer de nouveau le nombre de mine(s) avec un entier compris entre 1 et la 'longeur*largeur' - 1 ");
				mine = sc.nextInt();
			} else correct = true;
			}
		//Les dimensions et le nombre de mines sont maintenant valides.
			
	
			
		init(hauteur, largeur, mine); //Initialisation de la grille
		calculerAdjacent(); 
		//Donnons les informations indispensable à l'utilisateur afin qu'il puisse jouer : 
		System.out.println("AFIN DE JOUER : rentrer r (reveler) OU d (drapeau) PUIS le numéro de ligne (00 à 99) ET en dernier la lettre correspondant à la colonne (A à z)");
		System.out.println("exemple1 : r10F -> révèle case 2A ");
		System.out.println("exemple 2 : d02A -> marquer ou enlever drapeau sur case 2A");
		System.out.println("Bon jeu !");
		jeu();
		
		sc.close();
		
		
		
	}
	

	//
	// Exercice 5 bonus challenge : Pour aller plus loin
	//

	// Question 5.a] Optionnel
	public static void aide() {
		
		boolean helped = false;
		int not_revealed;
		int flag;
		System.out.println("\nVous semblez avoir besoin d'aide... Laissez nous vous aider !");
		System.out.println("Le système d'aide à la pose de drapeau est infaillible, mais celui qui vous permet de savoir quelles cases sont certaines de ne pas être des mines \nrepose sur votre"
				+ "placement de drapeau. Donc si vous en avez placé un au mauvais endroit, l'aide sera inutile voire dangereuse\n");
		//parcourons le tableau
		for(int i = 0; i < Tadj.length; i++) {
			for(int j = 0; j < Tadj[i].length; j++) {
				
				
				if(T[i][j] == 1 && Tadj[i][j] >= 1) { // Si la case de position (i,j) est révélée et possède au moins 1 mines adjacente.
					not_revealed = 0;
					//revealed = -1; // Car dans la boucle suivante, on compte la case elle-même (qui est forcément révélée à cause/grace au if précédent)
					flag = 0;
					
					// Collecte infos des cases adjacentes
					for(int x = i-1; x <= i+1; x++) { // Parours case adjacentes
						for(int y = j-1; y <= j+1; y++) {
							if(caseCorrecte(x, y)) {
								if(T[x][y] == 0) not_revealed++;
								else if(T[x][y] == 2) flag++;		
							}
						}
					}
					
					// Première aide - Bombes (Si il y a autant de cases non révélées que de bombes autour de la case, toutes les cases adjacentes non révélées sont des bombes)
					
					// Si il y a autant de cases non révélées que de bombes autour de la case (on compte les drapeaux pour être certain que le joueur n'a pas déjà posé un drapeau)
					// On précise
					if(not_revealed + flag == Tadj[i][j] && not_revealed != 0) { 
						// TODO : changer la phrase en fonction de si c'est singulier/pluriel ? (peut-etre trop compliqué)
						// TODO : Ajouter (si pas trop compliqué) une fonction qui converti les positions numériques en positions adaptées (lettre et nombre)
						helped = true;
						System.out.println("[!] La/les " + Tadj[i][j] + " case(s) non révélée(s) autour de la case " + i + ", " + j + " sont toutes des mines");
						}

					// Deuxième aide - Drapeaux (Si il y a autant de drapeaux que de bombes adjacentes autour d'une case, toutes les cases non révélées sont safe
					
					// On précise que le nombre de case(s) non révélée(s) doit être supérieur ou égal à 1 (pas égal à 0 plus compacte)			
					if(flag == Tadj[i][j] && not_revealed != 0) {
						helped = true;
						System.out.println("[X] La/les " + not_revealed + "case(s) autour de la case " + i + ", " + j + " (en dehors du/des drapeau(x) déjà positionné(s) ne sont pas des mines, "
								+ "tu peux les révéler !");
					}
				}
				
				
				
			}	
		}
		if(!helped) System.out.println("Nous ne pouvons pas apporter d'aide pour l'instant...");
		System.out.println();
		
		
	
	}

	// Question 5.b] Optionnel
	public static void intelligenceArtificielle() {
		
	}

	// Question 5.c] Optionnel
	public static void statistiquesVictoiresIA() {
		
	}

}