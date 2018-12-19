package pl.sstenzel.ug.javaee.florists.hibernate.service;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import pl.sstenzel.ug.javaee.florists.hibernate.domain.Flower;
import pl.sstenzel.ug.javaee.florists.hibernate.domain.Person;

import java.util.List;

@Component
@Transactional
public class FloristsServiceHibernateImpl implements FloristsService {

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
    public void clear() {
        sessionFactory.getCurrentSession().clear();
    }

    @Override
    public void deleteFlower(long id){
        Flower flower = this.getFlower(id);
        sessionFactory.getCurrentSession().delete(flower);
    }
    @Override
    public Flower updateFlower (long id, Flower flower){
        flower.setId(id);
        sessionFactory.getCurrentSession().update(flower);
        return  flower;
    }

    @Override
    public void addFlowers(List<Flower> flowers) {
        for( Flower f : flowers)
        sessionFactory.getCurrentSession().persist(f);
    }

    @Override
    public void addWaterman(long flowerId, Person waterman){
        sessionFactory.getCurrentSession().persist(waterman);
        Flower flower = (Flower) sessionFactory.getCurrentSession().get(Flower.class, flowerId);
        flower.addWaterman(waterman);
        waterman.addFlower(flower);
        sessionFactory.getCurrentSession().update(flower);
        sessionFactory.getCurrentSession().update(waterman);
    }

    @Override
    public void removeWaterman(long flowerId, long personId){
        Person waterman = (Person) sessionFactory.getCurrentSession().get(Person.class, personId);
        Flower flower = (Flower) sessionFactory.getCurrentSession().get(Flower.class, flowerId);
        flower.removeWaterman(waterman);
        waterman.removeFlower(flower);
        sessionFactory.getCurrentSession().update(flower);
        sessionFactory.getCurrentSession().update(waterman);
    }

    @Override
    public Person getPerson(long id){
        return (Person) sessionFactory.getCurrentSession().get(Person.class, id);
    }

    @Override
    public void deletePerson(long id){
        Person person = (Person) sessionFactory.getCurrentSession().get(Person.class, id);
        sessionFactory.getCurrentSession().delete(person);
    }




}
