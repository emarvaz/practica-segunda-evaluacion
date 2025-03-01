package modelo.servicio;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import modelo.entidades.SolicitudRegistro;
import java.util.List;

public class ServicioSolicitudRegistro {
    private EntityManagerFactory entityManagerFactory;

    public ServicioSolicitudRegistro(EntityManagerFactory entityManagerFactory) {
        this.entityManagerFactory = entityManagerFactory;
    }

    public EntityManager getEntityManager() {
        return entityManagerFactory.createEntityManager();
    }

    public void create(SolicitudRegistro notificacion) {
        EntityManager entityManager = getEntityManager();
        
        try {
            entityManager.getTransaction().begin();
            entityManager.persist(notificacion);
            entityManager.getTransaction().commit();
        } finally {
            entityManager.close();
        }
    }

    public List<SolicitudRegistro> buscarPendientes() {
        EntityManager entityManager = getEntityManager();
        
        try {
            return entityManager.createQuery("SELECT n FROM SolicitudRegistro n WHERE n.procesada = false ORDER BY n.fecha DESC", SolicitudRegistro.class).getResultList();
        } finally {
            entityManager.close();
        }
    }

    public void marcarComoProcesada(Long id) {
        EntityManager entityManager = getEntityManager();
        
        try {
            entityManager.getTransaction().begin();
            SolicitudRegistro notificacion = entityManager.find(SolicitudRegistro.class, id);
            if (notificacion != null) {
                notificacion.setProcesada(true);
            }
            entityManager.getTransaction().commit();
        } finally {
            entityManager.close();
        }
    }
}