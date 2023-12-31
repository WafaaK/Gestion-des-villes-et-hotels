package controllers;

import java.io.IOException;
import java.util.List;

import dao.IDaoHotel;
import dao.IDaoLocale;
import dao.IDaoVille;
import entities.Hotel;
import entities.Ville;
import jakarta.ejb.EJB;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/HotelController")
public class HotelController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @EJB
    private IDaoHotel ejb;
    @EJB
    private IDaoVille ejb2;

    public HotelController() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String action = request.getParameter("action");

        if (action != null) {
            List<Ville> villes = ejb2.findAllVilles();
            request.setAttribute("villes", villes);

            switch (action) {
                case "create":
                    creerHotel(request, response);
                    break;
                case "supprimer":
                    supprimerHotel(request, response);
                    break;
                case "modifier":
                    modifierHotel(request, response);
                    break;
                default:
                    // G�rer une action inconnue
                    afficherToutesHotels(request, response);
                    break;
            }
        } else {
            // Comportement par d�faut, afficher toutes les Hotels
            List<Ville> villes = ejb2.findAllVilles();
            request.setAttribute("villes", villes);

            afficherToutesHotels(request, response);
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Appeler la m�thode appropri�e pour traiter les requ�tes POST
        doGet(request, response);
    }

    private void creerHotel(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String nom = request.getParameter("nom");
        String adresse = request.getParameter("adresse");
        String telephone = request.getParameter("telephone");

        // Dans la m�thode creerHotel
        int villeId = Integer.parseInt(request.getParameter("selectedVille"));

        // Check if the parameters are not empty before creating a new hotel
        if (nom != null && !nom.isEmpty() && adresse != null && !adresse.isEmpty()
                && telephone != null && !telephone.isEmpty()) {

            // Utilisez la m�thode findById pour r�cup�rer l'objet Ville correspondant � l'ID
            Ville selectedVille = ejb2.findById(villeId);

            // Cr�ez l'objet Hotel en utilisant la ville r�cup�r�e
            Hotel newHotel = new Hotel(nom, adresse, telephone, selectedVille);

            // Appelez la m�thode create avec le nouvel objet Hotel
            ejb.create(newHotel);
        }

        // After creating the hotel, update the list of hotels and cities
        List<Ville> villes = ejb2.findAllVilles();
        request.setAttribute("villes", villes);

        afficherToutesHotels(request, response);
    }

    private void supprimerHotel(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String deleteId = request.getParameter("deleteId");

        if (deleteId != null && !deleteId.isEmpty()) {
            try {
                int idToDelete = Integer.parseInt(deleteId);
                Hotel hotelToDelete = ejb.findById(idToDelete);

                if (hotelToDelete != null) {
                    ejb.delete(hotelToDelete);
                    System.out.println("Hotel supprim�e avec succ�s.");
                } else {
                    System.out.println("Aucune Hotel trouv�e avec l'ID : " + idToDelete);
                }
            } catch (NumberFormatException e) {
                System.out.println("L'ID de la Hotel � supprimer n'est pas un nombre valide.");
            }
        } else {
            System.out.println("L'ID de la Hotel � supprimer est nul ou vide.");
        }

        // After deleting the hotel, update the list of hotels and cities
        List<Ville> villes = ejb2.findAllVilles();
        request.setAttribute("villes", villes);

        afficherToutesHotels(request, response);
    }

    private void modifierHotel(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String updateId = request.getParameter("updateId");
        String updatedNom = request.getParameter("updatedNom");
        String updatedAdresse = request.getParameter("updatedAdresse");
        String updatedTelephone = request.getParameter("updatedTelephone");

        if (updateId != null && !updateId.isEmpty() && updatedNom != null && !updatedNom.isEmpty()) {
            try {
                int idToUpdate = Integer.parseInt(updateId);
                Hotel hotelToUpdate = ejb.findById(idToUpdate);

                if (hotelToUpdate != null) {
                    // Mettez � jour les informations de l'h�tel
                    hotelToUpdate.setNom(updatedNom);
                    hotelToUpdate.setAdresse(updatedAdresse);
                    hotelToUpdate.setTelephone(updatedTelephone);

                    // Appelez la m�thode update
                    ejb.update(hotelToUpdate);
                    System.out.println("H�tel mise � jour avec succ�s.");
                } else {
                    System.out.println("Aucun H�tel trouv� avec l'ID : " + idToUpdate);
                }
            } catch (NumberFormatException e) {
                System.out.println("L'ID de la H�tel � mettre � jour n'est pas un nombre valide.");
            }
        } else {
            System.out.println("ID de mise � jour ou nom de la H�tel � mettre � jour est nul ou vide.");
        }

        // After updating the hotel, update the list of hotels and cities
        List<Ville> villes = ejb2.findAllVilles();
        request.setAttribute("villes", villes);

        afficherToutesHotels(request, response);
    }

    private void afficherToutesHotels(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setAttribute("Hotels", ejb.findAll());
        RequestDispatcher dispatcher = request.getRequestDispatcher("Hotel.jsp");
        dispatcher.forward(request, response);
    }
}
