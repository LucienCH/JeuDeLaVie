package JeuDeLaVie.Observateur;

public interface Observable {

    /**
     * Permet d'attahcer un observateur
     * @param o l'observateur
     */
    public void attacheObservateur(Observateur o);

    /**
     * Permet de dettacher un observateur
     * @param o l'observateur
     */
    public void detacheObservateur(Observateur o);

    /**
     * permet de nottifier tous les observateurs
     */
    public void notifieObservateurs();

}
