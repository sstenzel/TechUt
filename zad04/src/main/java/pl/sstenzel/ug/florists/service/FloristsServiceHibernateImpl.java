package pl.sstenzel.ug.florists.service;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import pl.sstenzel.ug.florists.domain.*;
import org.hibernate.Query;

import java.sql.Date;
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


// ===========================================================================

    @Override
    public void addFlower(Flower flower){
        sessionFactory.getCurrentSession().persist(flower);
    }

    @Override
    public Flower getFlower(long id){
        Flower flower =
                (Flower)sessionFactory.getCurrentSession().get(Flower.class, id);
        return flower;
    }
    @Override
    @SuppressWarnings("unchecked")
    public List<Flower> getAllFlowers(){
        List<Flower> flowers =
                (List<Flower>) sessionFactory.getCurrentSession().createCriteria(Flower.class).list();
        return flowers;
    }

    @Override
    public void deleteFlower(long id){
        Flower flower = this.getFlower(id);
        sessionFactory.getCurrentSession().delete(flower);
    }
    @Override
    public Flower updateFlower (Flower flower){
        sessionFactory.getCurrentSession().update(flower);
        return  flower;
    }

    @Override
    public void addFlowers(Flower[] flowers) {
        for( Flower f : flowers)
        sessionFactory.getCurrentSession().persist(f);
    }


// ===========================================================================

    @Override
    public void addDescription(Description description){
        sessionFactory.getCurrentSession().persist(description);
    }

    @Override
    public void addType(Type type) {
        sessionFactory.getCurrentSession().persist(type);
    }
    @Override
    public void addPerson(Person person){
        sessionFactory.getCurrentSession().persist(person);
    }


// ===========================================================================

    @Override
    public void setCareDescription(Flower flower, Description description){
        flower.setCareDescription(description);
        sessionFactory.getCurrentSession().update(flower);
    }

    public void removeCareDescription(Flower flower){
        flower.setCareDescription(null);
    }

    @Override
    public void addWaterman(Flower flower, Person person){
        flower.getPersons().add(person);
        sessionFactory.getCurrentSession().update(flower);
    }

    @Override
    public void removeWaterman(Flower flower, Person person){
        flower.getPersons().remove(person);
        sessionFactory.getCurrentSession().update(flower);
    }

    @Override
    public void setType(Flower flower, Type type){
        flower.setType(type);
        sessionFactory.getCurrentSession().update(flower);
    }

    @Override
    public void removeType(Flower flower){
        flower.setType(null);
        sessionFactory.getCurrentSession().update(flower);
    }


// ===========================================================================

    @Override
    @SuppressWarnings("unchecked")
    public List<Flower> findFlowersByDate (Date from, Date to) {
        Query query = sessionFactory.getCurrentSession().getNamedQuery("flower.dateBetween");
        query.setParameter("from", from);
        query.setParameter("to", to);
        return query.list();
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Flower> findByCareDescription (String description) {
        Query query = sessionFactory.getCurrentSession().getNamedQuery("flower.findByCareDescription");
        query.setParameter("description", description);
        return query.list();
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Person> getPersonByFlowerAmout (long amount) {
        Query query = sessionFactory.getCurrentSession().getNamedQuery("person.findByFlowers");
        query.setParameter("amount", amount);
        return query.list();
    }




}
