package in.co.sunrays.proj0.dao;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import in.co.sunrays.proj0.dto.SubjectDTO;
import in.co.sunrays.proj0.exception.DataBaseException;
import in.co.sunrays.proj0.exception.DuplicateRecordException;

/**
 * Subject Data Access Object provides Database CRUD operations. It is
 * implemented by plain Hibernate 3 API with Spring ORM.
 * 
 * All methods propagate unchecked DataAccessException. It is a generic
 * exception handling provided by Spring-DAO.
 * 
 * If DataAccessException is propagated from method then declarative transaction
 * is rolled back by Spring AOP.
 * 
 * This is plain Hibernate 3 API implementation of DAO
 * 
 * @author Iterator Copyright (c) Iterator
 */
@Repository("subjectDao")
public class SubjectDAOHibImpl implements SubjectDAOInt {
	Logger log = Logger.getLogger(SubjectDAOHibImpl.class);
	@Autowired
	private SessionFactory sessionFactory = null;

	public long add(SubjectDTO dto) throws DuplicateRecordException {
		log.debug("SubjectDAOHibImpl method add Start");
		long pk = 0;
		pk = (Long) sessionFactory.getCurrentSession().save(dto);
		log.debug("SubjectDAOHibImpl method add End");
		return pk;
	}

	public void update(SubjectDTO dto) throws DuplicateRecordException {
		log.debug("SubjectDAOHibImpl method Update Start");
		sessionFactory.getCurrentSession().update(dto);
		log.debug("SubjectDAOHibImpl method Update End");
	}

	public void delete(long id) throws DataBaseException {
		log.debug("SubjectDAOHibImpl method Delete Start");
		SubjectDTO dto = findByPk(id);
		sessionFactory.getCurrentSession().delete(dto);
		log.debug("SubjectDAOHibImpl method Delete End");
	}

	public SubjectDTO findByPk(long pk) throws DataBaseException {
		log.debug("SubjectDAOHibImpl method FindByPk Start");
		SubjectDTO dto = null;
		dto = (SubjectDTO) sessionFactory.getCurrentSession().get(SubjectDTO.class, pk);
		log.debug("SubjectDAOHibImpl method FindByPk End");
		return dto;
	}

	public SubjectDTO findByLogin(String login) throws DataBaseException {
		return null;
	}

	public SubjectDTO findByName(String name) throws DataBaseException {
		log.debug("SubjectDAOHibImpl method FindByName Start");
		SubjectDTO dto = null;
		List list = sessionFactory.getCurrentSession().createCriteria(SubjectDTO.class)
				.add(Restrictions.eq("subjectName", name)).list();
		if (list.size() == 1) {
			dto = (SubjectDTO) list.get(0);
		}
		log.debug("SubjectDAOHibImpl method FindByName End");
		return dto;
	}

	public List<SubjectDTO> search(SubjectDTO dto) throws DataBaseException {
		log.debug("SubjectDAOHibImpl method Search Start");
		List list = null;
		list = search(dto, 0, 0);
		log.debug("SubjectDAOHibImpl method Search End");
		return list;
	}

	public List<SubjectDTO> search(SubjectDTO dto, int pageNo, int pageSize) throws DataBaseException {
		log.debug("SubjectDAOHibImpl method Search With Pagination Start");
		List list = null;
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(SubjectDTO.class);
		if (dto != null) {
			if (dto.getId() > 0) {
				criteria.add(Restrictions.eq("id", dto.getId()));
			}
			if (dto.getCourseId() > 0) {
				criteria.add(Restrictions.eq("courseId", dto.getCourseId()));
			}
			if (dto.getSubjectName() != null) {
				criteria.add(Restrictions.like("subjectName", dto.getSubjectName() + "%"));
			}
			if (dto.getSemester() != null) {
				criteria.add(Restrictions.like("semester", dto.getSemester() + "%"));
			}
			if (dto.getDescription() != null) {
				criteria.add(Restrictions.like("description", dto.getDescription() + "%"));
			}
		}

		// if pageSize greater then zero then pagination apply

		if (pageSize > 0) {
			criteria.setFirstResult((pageNo - 1) * pageSize);
			criteria.setMaxResults(pageSize);
		}
		list = criteria.list();
		log.debug("SubjectDAOHibImpl method Search With Pagination End");
		return list;
	}

}
