package modelo.servicio;

import modelo.entidades.Solicitud;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;

public class ServicioSolicitud {
    private EntityManagerFactory entityManagerFactory;

    public ServicioSolicitud(EntityManagerFactory entityManagerFactory) {
        this.entityManagerFactory = entityManagerFactory;
    }

    private EntityManager getEntityManager() {
        return entityManagerFactory.createEntityManager();
    }

    public void crearSolicitud(Solicitud solicitud) {
        EntityManager entityManager = getEntityManager();
        
        try {
            entityManager.getTransaction().begin();
            entityManager.persist(solicitud);
            entityManager.getTransaction().commit();
        } finally {
            entityManager.close();
        }
    }

    public List<Solicitud> obtenerSolicitudesPendientes() {
        EntityManager entityManager = getEntityManager();
        try {
            TypedQuery<Solicitud> query = entityManager.createQuery("SELECT s FROM Solicitud s WHERE s.aprobado = false", Solicitud.class);
            return query.getResultList();
        } finally {
            entityManager.close();
        }
    }

    public Solicitud buscarPorId(Long id) {
        EntityManager entityManager = getEntityManager();
        try {
            return entityManager.find(Solicitud.class, id);
        } finally {
            entityManager.close();
        }
    }

    public void eliminarSolicitud(Long id) {
        EntityManager entityManager = getEntityManager();
        try {
            entityManager.getTransaction().begin();
            Solicitud solicitud = entityManager.find(Solicitud.class, id);
            if (solicitud != null) {
                entityManager.remove(solicitud);
            }
            entityManager.getTransaction().commit();
        } finally {
            entityManager.close();
        }
    }
}
