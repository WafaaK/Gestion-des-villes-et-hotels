import java.util.Hashtable;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import dao.IDaoRemote;
import entities.Hotel;

public class TestHotel {

    public static IDaoRemote<Hotel> lookUpEmployeRemote() throws NamingException {
        final Hashtable jndiProperties = new Hashtable();
        jndiProperties.put(Context.INITIAL_CONTEXT_FACTORY, "org.wildfly.naming.client.WildFlyInitialContextFactory");
        jndiProperties.put(Context.PROVIDER_URL, "http-remoting://localhost:8080");
        final Context context = new InitialContext(jndiProperties);

        return (IDaoRemote<Hotel>) context.lookup("ejb:ISICEJBEAR/ISICEJBServer/wafaa!dao.IDaoRemote");
    }

    public static void main(String[] args) {
        try {
            IDaoRemote<Hotel> dao = lookUpEmployeRemote();
//            dao.create(new Hotel("Ibis", "Adresse1", "08976535"));
//            dao.create(new Hotel("HayatRiginssi", "Adresse2", "0987654353"));
//            dao.create(new Hotel("Hotel1", "Adresse3", "0987654321"));

            // Supprimez le commentaire pour activer la suppression
            // dao.delete(dao.findById(1));

            // Affichez toutes les Hotels
            for (Hotel v : dao.findAll()) {
                System.out.println(v.getId() + " - " + v.getNom());
            }
        } catch (NamingException e) {
            e.printStackTrace();
        }

        try {
            IDaoRemote<Hotel> dao = lookUpEmployeRemote();

            // Créez une instance de Hotel avec un ID existant
            Hotel hotelToUpdate = new Hotel();
            hotelToUpdate.setId(6); // Remplacez 1 par l'ID réel de l'entité que vous souhaitez mettre à jour
            hotelToUpdate.setNom("modifier");

            // Appelez la méthode update
            Hotel updatedHotel = dao.update(hotelToUpdate);

            // Affichez le résultat
            if (updatedHotel != null) {
                System.out.println("Hotel mise à jour avec succès : ID = " + updatedHotel.getId() + ", Nom = " + updatedHotel.getNom());
            } else {
                System.out.println("La mise à jour de la Hotel a échoué.");
            }

            // Affichez toutes les Hotels après la mise à jour
            for (Hotel v : dao.findAll()) {
                System.out.println(v.getId() + " - " + v.getNom());
            }
        } catch (NamingException e) {
            e.printStackTrace();
        }
    }
}
