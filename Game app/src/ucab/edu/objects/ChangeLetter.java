package ucab.edu.objects;

public class ChangeLetter {
    public boolean setChangeLetter(Exchange exchange, Holder holder, String change) {
        Letter letter;
        if((letter = holder.searchLetter(change)) != null){
            holder.getTokensHold().remove(letter);
            exchange.getChangeLetters().add(letter);
            return true;
        }
        else{
            return false;
        }
    }
}
