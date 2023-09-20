package org.hibernate.bugs;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import java.util.Collection;
import java.util.UUID;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsInAnyOrder;
import org.hibernate.bugs.model.BaseReferenceEdge;
import org.hibernate.bugs.model.FormulaReferenceEdge;
import org.hibernate.testing.orm.junit.EntityManagerFactoryScope;
import org.hibernate.testing.orm.junit.Jpa;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Test;

@Jpa(
        annotatedClasses = {
            FormulaReferenceEdge.class,
            BaseReferenceEdge.class}
)
public class HHH17230Test {

    @AfterAll
    public void tearDown(EntityManagerFactoryScope scope) {
        scope.inTransaction(
                em -> {
                    em.createQuery("delete from FormulaReferenceEdge").executeUpdate();
                }
        );
    }

    @Test
    public void hhh17230Test(EntityManagerFactoryScope scope) throws Exception {
        scope.inTransaction(em -> {
            UUID sourceId = UUID.randomUUID();
            UUID targetId = UUID.randomUUID();
            Query insertQuery = em.createQuery("insert into FormulaReferenceEdge fr (fr.sourceId,fr.targetId) values (?1,?2)");
            insertQuery.setParameter(1, sourceId);
            insertQuery.setParameter(2, targetId);
            int inserted = insertQuery.executeUpdate();
            assert inserted != 0;
            flushAndClear(em);

            Collection<FormulaReferenceEdge> actualFormulaReferenceEdges = findAllFormulaReferenceEdges(em);

            assertThat(actualFormulaReferenceEdges, containsInAnyOrder(new FormulaReferenceEdge(sourceId, targetId)));
        });
    }

    public void flushAndClear(EntityManager em) {
        em.flush();
        em.clear();
    }

    private Collection<FormulaReferenceEdge> findAllFormulaReferenceEdges(EntityManager em) {
        Query query = em.createQuery("select formulaReferenceEdge "
                + "from FormulaReferenceEdge formulaReferenceEdge ");
        return query.getResultList();
    }
}
