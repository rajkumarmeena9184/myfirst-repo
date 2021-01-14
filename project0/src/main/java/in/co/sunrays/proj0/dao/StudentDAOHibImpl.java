package in.co.sunrays.proj0.dao;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import in.co.sunrays.proj0.dto.StudentDTO;
import in.co.sunrays.proj0.exception.DataBaseException;
import in.co.sunrays.proj0.exception.DuplicateRecordException;

/**
 * Student Data Access Object provides Database CRUD operations. It is
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
@Repository("studentDao")
public class StudentDAOHibImpl implements StudentDAOInt {
	Logger log = Logger.getLogger(StudentDAOHibImpl.class);
	@Autowired
	private SessionFactory sessionFactory = null;

	public long add(StudentDTO dto) throws DuplicateRecordException {
		log.debug("StudentDAOHibImpl method Add Start");
		long pk = 0;
		pk = (Long) sessionFactory.getCurrentSession().save(dto);
		log.debug("StudentDAOHibImpl method Add end");
		return pk;
	}

	public void update(StudentDTO dto) throws DuplicateRecordException {
		log.debug("StudentDAOHibImpl method update Start");
		sessionFactory.getCurrentSession().update(dto);
		log.debug("StudentDAOHibImpl method update End");
	}

	public void delete(long id) throws DataBaseException {
		log.debug("StudentDAOHibImpl method Delete Start");
		StudentDTO dto = findByPk(id);
		sessionFactory.getCurrentSession().delete(dto);
		log.debug("StudentDAOHibImpl method Delete end");
	}

	public StudentDTO findByPk(long pk) throws DataBaseException {
		log.debug("StudentDAOHibImpl method FindByPk Start");
		StudentDTO dto = null;
		dto = (StudentDTO) sessionFactory.getCurrentSession().get(StudentDTO.class, pk);
		log.debug("StudentDAOHibImpl method FindByPk End");
		return dto;
	}

	public StudentDTO findByLogin(String login) throws DataBaseException {
		log.debug("StudentDAOHibImpl method FindByLogin Start");
		StudentDTO dto = null;
		List list = sessionFactory.getCurrentSession().createCriteria(StudentDTO.class)
				.add(Restrictions.eq("emailId", login)).list();
		if (list.size() == 1) {
			dto = (StudentDTO) list.get(0);
		}
		log.debug("StudentDAOHibImpl method FindByLogin End");
		return dto;
	}

	public List<StudentDTO> search(StudentDTO dto) throws DataBaseException {
		log.debug("StudentDAOHibImpl method search Start");
		List list = null;
		list = search(dto, 0, 0);
		log.debug("StudentDAOHibImpl method search End");
		return list;
	}

	public List<StudentDTO> search(StudentDTO dto, int pageNo, int pageSize) throws DataBaseException {
		log.debug("StudentDAOHibImpl method search Start");
		List list = null;
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(StudentDTO.class);
		if (dto != null) {
			if (dto.getId() > 0) {
				criteria.add(Restrictions.eq("id", dto.getId()));
			}

			if (dto.getCollegeId() > 0) {
				criteria.add(Restrictions.eq("collegeId", dto.getCollegeId()));
			}
			if (dto.getFirstName() != null) {
				criteria.add(Restrictions.like("firstName", dto.getFirstName() + "%"));
			}
			if (dto.getLastName() != null) {
				criteria.add(Restrictions.like("lastName", dto.getLastName() + "%"));
			}
			if (dto.getEmailId() != null) {
				criteria.add(Restrictions.like("emailId", dto.getEmailId() + "%"));
			}
		}

		// if pageSize greater then zero then pagination apply

		if (pageSize > 0) {
			criteria.setFirstResult((pageNo - 1) * pageSize);
			criteria.setMaxResults(pageSize);
		}
		list = criteria.list();
		log.debug("StudentDAOHibImpl method search End");
		return list;
	}

}
