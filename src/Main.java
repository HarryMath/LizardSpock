public class Main {
    public static void main(String[] args) {
        Game game = new Game(args);
        GameKey gameKey = new GameKey();
	    int computer_move = game.makeMove();
        System.out.println("HMAC:\n" + gameKey.getHashMac(game.options[computer_move]));
        game.printMoves();
        int player_move = game.getPlayerMove();
	    if(player_move >= 0) {
            game.play(computer_move, player_move);
            System.out.println("HMAC key: " + gameKey.getKey());
        } // else option "exit" chosen
    }
}
