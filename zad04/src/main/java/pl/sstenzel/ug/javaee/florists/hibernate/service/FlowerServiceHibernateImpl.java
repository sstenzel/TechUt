package pl.sstenzel.ug.javaee.florists.hibernate.service;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import pl.sstenzel.ug.javaee.florists.hibernate.domain.Flower;

import java.util.List;

@Component
@Transactional
public class FlowerServiceHibernateImpl implements FlowerService{

    @Autowired
    private SessionFactory sessionFactory;

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void addFlower(Flower flower){
//        flower.setId(null);
        sessionFactory.getCurrentSession().persist(flower);
    }

    @Override
    public Flower getFlower(long id){
        Flower flower = (Flower)sessionFactory.getCurrentSession().get(Flower.class, id);
        return flower;
    }


    @Override
    public List<Flower> getAllFlowers(){
        List<Flower> flowers = sessionFactory.getCurrentSession().getNamedQuery("flower.all").list();
        return flowers;
    }


    @Override
    public void deleteAllFlowers() {
        sessionFactory.getCurrentSession().clear();
    }

    @Override
    public void deleteFlower(long id){
        Flower flower = this.getFlower(id);
        sessionFactory.getCurrentSession().delete(flower);
    }

    @Override
    public Flower updateFlower (long id){
        Flower flower = this.getFlower(id);
        sessionFactory.getCurrentSession().update(flower);
        return  flower;
    }

    @Override
    public void addFlowers(List<Flower> flowers) {
        for( Flower f : flowers)
        sessionFactory.getCurrentSession().persist(f);
    }


}
