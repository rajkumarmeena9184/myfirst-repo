package in.co.sunrays.proj0.dao;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import in.co.sunrays.proj0.dto.CourseDTO;
import in.co.sunrays.proj0.exception.DataBaseException;
import in.co.sunrays.proj0.exception.DuplicateRecordException;

/**
 * Course Data Access Object provides Database CRUD operations. It is
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
@Repository("courseDao")
public class CourseDAOHibImpl implements CourseDAOInt {
	Logger log=Logger.getLogger(CourseDAOHibImpl.class);
	@Autowired
	private SessionFactory sessionFactory = null;

	public long add(CourseDTO dto) {
		log.debug("CourseDAoHibImpl Method Add Started");
		long pk = (Long) sessionFactory.getCurrentSession().save(dto);
		log.debug("CourseDAoHibImpl Method Add End");
		return pk;
	}

	public void update(CourseDTO dto) throws DuplicateRecordException {
		log.debug("CourseDAoHibImpl Method Update Started");
		sessionFactory.getCurrentSession().update(dto);
		log.debug("CourseDAoHibImpl Method Update End");
	}

	public void delete(long id) throws DataBaseException {
		log.debug("CourseDAoHibImpl Method delete Started");
		CourseDTO dto = findByPk(id);
		sessionFactory.getCurrentSession().delete(dto);
		log.debug("CourseDAoHibImpl Method delete end");
	}

	public CourseDTO findByPk(long pk) {
		log.debug("CourseDAoHibImpl Method FindByPk Started");
		CourseDTO dto = null;
		dto = (CourseDTO) sessionFactory.getCurrentSession().get(CourseDTO.class, pk);
		log.debug("CourseDAoHibImpl Method FindByPk End");
		return dto;
	}

	public CourseDTO findByName(String name) {
		log.debug("CourseDAoHibImpl Method FindByName Started");
		CourseDTO dto = null;
		List list = sessionFactory.getCurrentSession().createCriteria(CourseDTO.class)
				.add(Restrictions.eq("name", name)).list();
		if (list.size() == 1) {
			dto = (CourseDTO) list.get(0);
		}
		log.debug("CourseDAoHibImpl Method FindByName End");
		return dto;
	}

	public List<CourseDTO> search(CourseDTO dto) throws DataBaseException {
		log.debug("CourseDAoHibImpl Method Search Started");
		List list = null;
		list = search(dto, 0, 0);
		log.debug("CourseDAoHibImpl Method Search End");
		return list;
	}

	public List<CourseDTO> search(CourseDTO dto, int pageNo, int pageSize) throws DataBaseException {
		log.debug("CourseDAoHibImpl Method Search with Pagination Started");
		List list = null;
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(CourseDTO.class);
		if (dto != null) {
			if (dto.getId() > 0) {
				criteria.add(Restrictions.eq("id", dto.getId()));
			}
			if (dto.getName() != null) {
				criteria.add(Restrictions.like("name", dto.getName() + "%"));
			}
			if (dto.getDescription() != null) {
				criteria.add(Restrictions.like("description", dto.getDescription() + "%"));
			}
		}
		// if page size is greater than zero the apply pagination
		if (pageSize > 0) {
			criteria.setFirstResult((pageNo - 1) * pageSize);
			criteria.setMaxResults(pageSize);
		}
		list = criteria.list();
		log.debug("CourseDAoHibImpl Method Search with Pagination End");
		return list;
	}

}
