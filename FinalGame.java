//Name: Lambert
//Date: 2015.6.18

package final1;

import java.util.Scanner;

import javax.swing.JOptionPane;

public class FinalGame {

	public static final String START_MSG = "Welcome to the game!\n"
			+ "You are the prince, your goal is to escape from the castle and save the princess\n"
			+ "in the castle. To do that, you need to find the princess and then find the key \n"
			+ "You and the princess have 50 health each \n"
			+ "If you died, the game is over! \n"
			+ "If you beat the monster but the princess died,\n "
			+ "you didn't complete the mission but you can still eascape from the castle!\n";

	public static int[][] castle = createCastle();

	public static int position = 0;

	public static boolean inRoom;
	public static boolean hasWeapon = false;

	// Character properties
	public static String name;
	public static boolean isPrincessPresent = false;
	public static boolean isPrincessAlive = true;
	public static boolean hasKey = false;
	public static boolean hasDrink = false;
	public static boolean beatMonster = false;
	public static boolean decreasePrinceHealth = false;
	public static boolean decreasePrincessHealth = false;

	public static Scanner input = new Scanner(System.in);

	public static void main(String[] args) {
		System.out.println(START_MSG);
		System.out.println("Please choose your prince's name! Good Luck!");
		name = input.nextLine();
		System.out.println("You are the prince " + name);
		System.out
				.println("\nThere are total 8 rooms with 2 rooms on each row\n"
						+ "\n Now, you are at the bottom of the center road, there is a room at your left "
						+ "\n and a room at your right, Let's go forward!");
		System.out.println("\n Type w (forward)," + "\n s (back)"
				+ "\n a (turn left)" + "\n d(turn right)"
				+ "\n status(check status)" + "\n quit(quit the game)");
		while (!hasKey || !isPrincessPresent)
			castleAction(input.nextLine());

		System.out.println("Thanks for playing the game.");

		if (!beatMonster && isPrincessAlive) {
			System.out.println("Congratulations! "
					+ "\n You have found the key and princess!"
					+ "\n You escaped from the building!"
					+ "\n You have completed a silver medal!"
					+ "The game is over!");
			System.exit(0);
		} else if (beatMonster && isPrincessAlive) {
			System.out.println("Congratulations!"
					+ "\n You found the key and the princess"
					+ "\n You beat the monster in the castle!"
					+ "\n You escaped and completed a gold medal!"
					+ "\n The game is over!");
			System.exit(0);
		} else if (beatMonster && !isPrincessAlive) {
			System.out.println("Congratulations!"
					+ "\n You found the key and the princess"
					+ "\n You beat the monster in the castle!"
					+ "\n The princess is dead!"
					+ "\n You escaped and completed a copper medal!"
					+ "\n The game is over!");
			System.exit(0);
		}
	}

	public static void castleAction(String cmd) {
		switch (cmd) {
		case "w":
			if (position < 3) {
				position++;
			} else {
				System.out.println("End of the castle");
			}
			break;

		case "s":
			if (position > 0) {
				position--;
			} else {
				System.out.println("End of the castle");
			}
			break;

		case "a":
			enterRoom(castle[position][0]);
			break;

		case "d":
			enterRoom(castle[position][1]);
			break;

		case "status":
			status();
			break;

		default:
			generalAction(cmd);
		}
	}

	public static void generalAction(String actionCmd) {
		switch (actionCmd) {

		case "quit":
			System.exit(0);

		default:
			System.out.println("Invalid input");
			break;
		}
	}

	public static int[][] createCastle() {
		int[][] castle = new int[4][2];

		for (int num = 1; num <= 7; num++) {
			int x, y;
			do {
				x = (int) (Math.random() * 4);
				y = (int) (Math.random() * 2);
			} while (castle[x][y] != 0);
			castle[x][y] = num;
		}

		return castle;
	}

	public static void status() {
		System.out.println("find weapon : " + hasWeapon + "\nfind key : "
				+ hasKey + "\nfind princess : " + isPrincessPresent
				+ "\nfind drink : " + hasDrink + "\nbeat monster : "
				+ beatMonster + "\nprince health decrease 10  : "
				+ decreasePrinceHealth + "\nprincess health decrease 10  : "
				+ decreasePrincessHealth);
	}

	public static void enterRoom(int roomNum) {

		System.out.println("Entering room " + roomNum);
		inRoom = true;
		switch (roomNum) {
		case 1:
			princessRoom();

			break;
		case 2:
			monsterRoom();
			break;
		case 3:
			weaponRoom();
			break;
		case 4:
			keyRoom();
			break;
		case 5:
			drinkRoom();
			break;
		case 6:
			normalRoom1();
			break;
		case 7:
			normalRoom2();
			break;
		case 0:
			magicianRoom();
			break;

		}

	}

	public static void keyRoom() {
		if (hasKey) {
			System.out.println("You have already found the key!");
			return;
		}

		System.out.println("This is a teasure room");
		System.out
				.println("Type look(look around), or out(get out of the room).");

		while (inRoom) {
			String cmd = input.nextLine();
			switch (cmd) {
			case "look":
				System.out
						.println("There is a big box on the table, what is it?");
				System.out.println("Wow! I have find the key!"
						+ "\ntype 'out' to get out of the room");
				break;

			case "out":
				inRoom = false;
				break;

			default:
				generalAction(cmd);
			}
		}

		hasKey = true;

		if (isPrincessPresent) {
			System.out.println("Now, you can get out of the castle!");
		} else {
			System.out.println("Now I can keep finding the princess");
		}

	}

	public static void drinkRoom() {

		if (hasDrink) {
			System.out.println("You have already find the Drink!");
			return;
		}

		System.out.println("This is the drink room");

		System.out
				.println("Type look(look around), or out(get out of the room).");

		while (inRoom) {
			String cmd = input.nextLine();
			switch (cmd) {
			case "look":
				System.out.println("There is a cup of milk tea! So wonderful!");
				System.out
						.println("Wow! I have found the powerful drink!\n"
								+ "I can increase my health ot princess health by 10 during the fight!\n"
								+ "\ntype 'out' to get out of the room");
				break;

			case "out":
				inRoom = false;
				break;

			default:
				generalAction(cmd);
			}
		}

		hasDrink = true;

	}

	public static void magicianRoom() {
		System.out.println("This is the magician room");

		System.out.println("Type look, talk or out.");

		while (inRoom) {
			String cmd = input.nextLine();
			switch (cmd) {
			case "look":
				System.out
						.println("There is a strange person over there, who is him?");
				break;

			case "out":
				System.out
						.println("Hey! Don't leave! I have something to say!");
			case "talk":
				System.out
						.println("Hi! I am the magician in the castle!\n"
								+ "Nice to meet you! I am going to ask you a question!\n"
								+ "You have the only choice to decide your destiny!");

				System.out
						.println("\n There is an old story!\n"
								+ "A princess stayed in the castle for a long time until one day"
								+ "\n a brave prince came to save her!\n"
								+ "Unfortunately, there is a ghost live in the castle,"
								+ "\n he said that, the princess in the white dress will never"
								+ "\n get out of the castle! "
								+ "\n during that day, the princess was in the white dress."
								+ "\n At last, the princess escape the castle!"
								+ "\n How did she do that?");
				System.out.println("\n a. She killed the ghost!"
						+ "\n b. She killed the prince"
						+ "\n c. She killed herself!");

				boolean repeat = true;
				while (repeat) {
					repeat = false;
					System.out
							.println("\n you should type a , b or c for your answer!");
					String playerChoose = input.nextLine();
					switch (playerChoose) {
					case "a":
						System.out
								.println("How can that be possible? The ghost will never die!"
										+ "\n Your answer is incorrect! Your health is decreased by 10! Good luck!");
						decreasePrinceHealth = true;
						break;

					case "b":
						System.out
								.println("The princess killed the prince and she used "
										+ "\n his blood to dye her dress into red! Then she escaped!"
										+ "\n I can't believe you got the right answer!"
										+ "\n I wish you can successfully escape from the castle with your princess");
						break;
					case "c":
						System.out
								.println("I feel really diasappointed about you!"
										+ "\n Your answer is incorrect! The princess's is decreased by 10! Good luck!");
						decreasePrincessHealth = true;
						break;
					default:
						System.out.println("invalid input");
						repeat = true;
						break;
					}

				}
				
				System.out.println("Please type w , a , s , d , status or quit.");
				inRoom = false;
				break;

			default:
				generalAction(cmd);
			}
		}
	}

	public static void normalRoom2() {
		System.out.println("This is a ??? room (Be careful!)");

		System.out
				.println("Type look(look around), or out(get out of the room).");

		while (inRoom) {
			String cmd = input.nextLine();
			switch (cmd) {
			case "look":
				System.out.println("I can see the blood on the floor!"
						+ "\n  I can feel something is getting closer!"
						+ "\ntype 'out' to get out of the room");
				break;

			case "out":
				inRoom = false;
				break;

			default:
				generalAction(cmd);
			}
		}

	}

	public static void normalRoom1() {
		System.out.println("This is a ??????? room! (Are you afraid)?");

		System.out
				.println("Type look(look around), or out(get out of the room).");

		while (inRoom) {
			String cmd = input.nextLine();
			switch (cmd) {
			case "look":
				System.out
						.println("I am watching you! My prince! Come here! Hah Hah!(Monster's smile)"
								+ "\ntype 'out' to get out of the room");
				break;

			case "out":
				inRoom = false;
				break;

			default:
				generalAction(cmd);
			}
		}

	}

	public static void weaponRoom() {

		if (hasWeapon) {
			System.out.println("You have already found the Weapon!");
			return;
		}

		System.out.println("This is the lucky room");

		System.out
				.println("Type look(look around), or out(get out of the room).");

		while (inRoom) {
			String cmd = input.nextLine();
			switch (cmd) {
			case "look":
				System.out
						.println("There is somsthing shining on the floor! What is it?");
				System.out.println("Wow, I have found a super sword!\n"
						+ "My attack damage has increased by 10!"
						+ "\ntype 'out' to get out of the room");
				break;

			case "out":
				inRoom = false;
				break;

			default:
				generalAction(cmd);
			}
		}

		hasWeapon = true;

	}

	public static void monsterRoom() {
		System.out.println("You are in the monster room now!\n"
				+ "There must be a winner before you come out!");

		System.out.println("\nHere is your current status!\n");
		status();

		int monsterHealth = 100;
		int princeHealth = 100;
		int princessHealth = 50;

		if (decreasePrinceHealth) {
			princeHealth = 90;
		}

		if (decreasePrincessHealth) {
			princessHealth = 40;
		}

		while (monsterHealth > 0 && princeHealth > 0) {
			// 20-25
			final int ATTACK1 = (int) Math.random() * 6 + 20;
			// 30-35 for prince's weapon
			final int ATTACK2 = (int) Math.random() * 6 + 30;
			// 20-30 for monster's damage to prince
			final int ATTACK3 = (int) Math.random() * 11 + 25;
			// 5-10 for monster's damage to princess
			final int ATTACK4 = (int) Math.random() * 6 + 5;
			final int HEAL = 10;
			// 5-10 for monster's heal
			final int MONSTER_HEAL = (int) Math.random() * 6 + 5;
			if (isPrincessPresent) {
				System.out.println(" prince's health is " + princeHealth
						+ "\n princess health is " + princessHealth
						+ "\n monster health is " + monsterHealth);
			} else {
				System.out.println(" prince's health is " + princeHealth
						+ "\n monster health is " + monsterHealth);

			}

			System.out.println("\n prince's turn begins!");
			boolean notvalid = true;
			while (notvalid) {

				notvalid = false;// reset to false
				System.out
						.println("Choose the type of attack that you want\n"
								+ " you should type hit, sword(if you have found a weapon),"
								+ "\n princeDrink or princessDrink(if you have found the drink)\n"
								+ " watch your spelling and space!");
				String typeOfAttack = "";
				typeOfAttack = input.nextLine();
				// calculate the result after each results

				switch (typeOfAttack) {
				case "hit":
					monsterHealth -= ATTACK1;
					System.out.println(" prince uses hit to attack monster "
							+ ATTACK1);
					break;
				case "sword":
					if (hasWeapon) {
						monsterHealth -= ATTACK2;
						System.out
								.println(" prince uses sword to attack monster "
										+ ATTACK2);
					} else {
						System.out.println("Sorry, you don't have the weapon!");
						notvalid = true;

					}
					break;
				case "princeDrink":
					if (hasDrink) {
						System.out.println("Prince increase his health by 10");
						princeHealth += HEAL;
						hasDrink = false;
					} // repeat
					else {
						System.out
								.println("Either you didn't find the drink or you have used it!");
						notvalid = true;
					}
					break;
				case "princessDrink":
					if (hasDrink && isPrincessPresent) {
						System.out
								.println("Prince increase princess health by 10");
						princessHealth += HEAL;
						hasDrink = false;
					} // repeat
					else {
						System.out
								.println("You might not find princess or you have used it already or you don't have the drink ");
						notvalid = true;
					}
					break;
				default:
					System.out.println("Invalid Input! Please try it again");

					notvalid = true;
				}
			}
			if (princeHealth > 100) {
				princeHealth = 100;
			}

			if (princessHealth > 50) {
				princessHealth = 50;
			}

			// check if there is player dead
			if (princeHealth <= 0 || princessHealth <= 0 || monsterHealth <= 0) {
				break;
			}
			System.out.println(" Prince's turn is over! ");

			System.out.println(" \n Monster's turn begins!");
			int monsterAttack = (int) (Math.random() * 3);

			// determine the situation and the results after each type of
			// attack
			switch (monsterAttack) {
			case 0:
				princeHealth -= ATTACK3;
				System.out.println("Monster uses hit to attack prince "
						+ ATTACK3);
				if (isPrincessPresent) {
					princessHealth -= ATTACK4;
					System.out
							.println("\n Monster uses hit to attack princess "
									+ ATTACK4);
				}

				break;

			case 1:
				princeHealth -= ATTACK3;
				System.out.println("Monster uses hit to attack prince "
						+ ATTACK3);
				if (isPrincessPresent) {
					princessHealth -= ATTACK4;
					System.out
							.println("\n Monster uses hit to attack princess "
									+ ATTACK4);
				}

				break;

			case 2:
				monsterHealth += MONSTER_HEAL;
				if (monsterHealth > 100) {
					monsterHealth = 100;
				}
				System.out.println("  monster heals himself for "
						+ MONSTER_HEAL);
				break;

			}

			System.out.println(" Monster's turn is over! ");

			if (princeHealth < 25 || princessHealth < 10) {
				int option = JOptionPane
						.showConfirmDialog(null,
								"Either your health < 25 or princess health < 10! Continue the game?");

				// End the program if the player give up
				if (option != JOptionPane.YES_OPTION) {

					break;
				}

			}
		}

		if (princeHealth > 0 && princessHealth > 0 && monsterHealth > 0) {
			// Surrender
			System.out.println(" prince surrenders! "
					+ "\n prince remaing health is " + princeHealth
					+ "\n princess remaning health is " + princessHealth
					+ "\n monster remaning health is " + monsterHealth);
			System.out
					.println("You surrender! How weak you are! The game is over!");
			System.exit(0);
		} else if (princeHealth > 0 && princessHealth > 0 && monsterHealth <= 0) {
			// both wins\
			if (isPrincessPresent) {
				System.out.println(" prince and princess wins! "
						+ "\n prince remaing health is " + princeHealth
						+ "\n princess remaing health is " + princessHealth);
			} else {
				System.out.println(" prince beat the monster! "
						+ "\n prince remaing health is " + princeHealth
						+ "\n princess remaing health is " + princessHealth);
				System.out.println("You shoud keep finding princess!");
				System.out.println("type w, a, s, d status or quit");
			}
			beatMonster = true;
		} else if (princeHealth > 0 && princessHealth <= 0
				&& monsterHealth <= 0) {
			// princess died
			System.out.println(" prince beat the monster but princess died! "
					+ "\n prince remaing health is " + princeHealth);
			beatMonster = true;
			isPrincessAlive = false;
			System.out.println("type w, a, s, d status or quit");
		} else if (princeHealth <= 0 && monsterHealth > 0) {
			// monster wins
			System.out.println(" prince failed! Game over! "
					+ "\n monster remaning health is " + monsterHealth
					+ "\n princess remaning health is " + princessHealth);
			System.out.println("You are died! Your mission is failed!");
			System.exit(0);

		}

	}

	public static void princessRoom() {

		if (isPrincessPresent) {
			System.out.println("You have already found the princess!");
			return;
		}

		System.out.println("You have found the princess.");
		isPrincessPresent = true;
		System.out.println("Type talk or out.");

		while (inRoom) {
			String cmd = input.nextLine();
			switch (cmd) {
			case "talk":
				System.out.println("Hi,");
				System.out
						.println("I wait you for a long time! Now, Let's get out of this castle!"
								+ "\ntype 'out' to get out of the room");
				break;

			case "out":
				inRoom = false;
				break;

			default:
				generalAction(cmd);
			}
		}
	}

}