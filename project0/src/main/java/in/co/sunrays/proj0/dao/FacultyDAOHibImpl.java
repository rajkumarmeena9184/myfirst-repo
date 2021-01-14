package in.co.sunrays.proj0.dao;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import in.co.sunrays.proj0.dto.FacultyDTO;
import in.co.sunrays.proj0.exception.DataBaseException;
import in.co.sunrays.proj0.exception.DuplicateRecordException;

/**
 * Faculty Data Access Object provides Database CRUD operations. It is
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
@Repository("facultyDao")
public class FacultyDAOHibImpl implements FacultyDAOInt {
	Logger log = Logger.getLogger(FacultyDAOHibImpl.class);
	@Autowired
	private SessionFactory sessionFactory = null;

	public long add(FacultyDTO dto) throws DuplicateRecordException {
		log.debug("FacultyDAOHibImpl method Add Started");
		long pk = 0;
		pk = (Long) sessionFactory.getCurrentSession().save(dto);
		log.debug("FacultyDAOHibImpl method Add end");
		return pk;
	}

	public void update(FacultyDTO dto) throws DuplicateRecordException {
		log.debug("FacultyDAOHibImpl method update Started");
		sessionFactory.getCurrentSession().update(dto);
		log.debug("FacultyDAOHibImpl method update end");
	}

	public void delete(long id) throws DataBaseException {
		log.debug("FacultyDAOHibImpl method delete Started");
		FacultyDTO dto = findByPk(id);
		sessionFactory.getCurrentSession().delete(dto);
		log.debug("FacultyDAOHibImpl method delete end");
	}

	public FacultyDTO findByPk(long pk) throws DataBaseException {
		log.debug("FacultyDAOHibImpl method findByPk Started");
		FacultyDTO dto = null;
		dto = (FacultyDTO) sessionFactory.getCurrentSession().get(FacultyDTO.class, pk);
		log.debug("FacultyDAOHibImpl method findByPk end");
		return dto;
	}

	public FacultyDTO findByLogin(String login) throws DataBaseException {
		log.debug("FacultyDAOHibImpl method findByLogin Started");
		FacultyDTO dto = null;
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(FacultyDTO.class);
		criteria.add(Restrictions.like("emailID", login));
		List list = criteria.list();
		if (list.size() == 1) {
			dto = (FacultyDTO) list.get(0);
		}
		log.debug("FacultyDAOHibImpl method findByLogin end");
		return dto;
	}

	public List<FacultyDTO> search(FacultyDTO dto) throws DataBaseException {
		log.debug("FacultyDAOHibImpl method search Started");
		List list = null;
		list = search(dto, 0, 0);
		log.debug("FacultyDAOHibImpl method search end");
		return list;
	}

	public List<FacultyDTO> search(FacultyDTO dto, int pageNo, int pageSize) throws DataBaseException {
		log.debug("FacultyDAOHibImpl method search  with pagination Started");
		List list = null;
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(FacultyDTO.class);
		if (dto != null) {
			if (dto.getId() > 0) {
				criteria.add(Restrictions.eq("id", dto.getId()));
			}
			if (dto.getFirstName() != null) {
				criteria.add(Restrictions.like("firstName", dto.getFirstName() + "%"));
			}
			if (dto.getLastName() != null) {
				criteria.add(Restrictions.like("lastName", dto.getLastName() + "%"));
			}
			if (dto.getEmailID() != null) {
				criteria.add(Restrictions.like("emailID", dto.getEmailID() + "%"));
			}
		}
		// if page size is greater than zero the apply pagination
		if (pageSize > 0) {
			criteria.setFirstResult((pageNo - 1) * pageSize);
			criteria.setMaxResults(pageSize);
		}
		list = criteria.list();
		log.debug("FacultyDAOHibImpl method search  with pagination end");
		return list;
	}

}
