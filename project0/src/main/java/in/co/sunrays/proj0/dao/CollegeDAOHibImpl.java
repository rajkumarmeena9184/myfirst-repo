package in.co.sunrays.proj0.dao;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import in.co.sunrays.proj0.dto.CollegeDTO;
import in.co.sunrays.proj0.exception.DataBaseException;
import in.co.sunrays.proj0.exception.DuplicateRecordException;

/**
 * College Data Access Object provides Database CRUD operations. It is
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
@Repository(value = "collegeDao")
public class CollegeDAOHibImpl implements CollegeDAOInt {
	Logger log = Logger.getLogger(CollegeDAOHibImpl.class);
	@Autowired
	private SessionFactory sessionFactory = null;

	public long add(CollegeDTO dto) throws DuplicateRecordException {
		log.debug("CollegeDaoHibImpl method add Started");
		long pk = (Long) sessionFactory.getCurrentSession().save(dto);
		log.debug("CollegeDaoHibImpl method add End");
		return pk;
	}

	public void update(CollegeDTO dto) throws DuplicateRecordException {
		log.debug("CollegeDaoHibImpl method update Started");
		sessionFactory.getCurrentSession().update(dto);
		log.debug("CollegeDaoHibImpl method update End");
	}

	public void delete(long id) throws DataBaseException {
		log.debug("CollegeDaoHibImpl method Delete Started");
		CollegeDTO dto = findByPk(id);
		sessionFactory.getCurrentSession().delete(dto);
		log.debug("CollegeDaoHibImpl method Delete End");
	}

	public CollegeDTO findByPk(long pk) throws DataBaseException {
		log.debug("CollegeDaoHibImpl method FindByPk Started");
		CollegeDTO dto = null;
		dto = (CollegeDTO) sessionFactory.getCurrentSession().get(CollegeDTO.class, pk);
		log.debug("CollegeDaoHibImpl method FindByPk End");
		return dto;
	}

	public CollegeDTO findByName(String name) throws DataBaseException {
		log.debug("CollegeDaoHibImpl method FindByName Started");
		CollegeDTO dto = null;
		List<CollegeDTO> list = sessionFactory.getCurrentSession().createCriteria(CollegeDTO.class)
				.add(Restrictions.eq("name", name)).list();
		if (list.size() == 1) {
			dto = (CollegeDTO) list.get(0);
		}
		log.debug("CollegeDaoHibImpl method FindByName End");
		return dto;
	}

	public List<CollegeDTO> search(CollegeDTO dto) throws DataBaseException {
		log.debug("CollegeDaoHibImpl method Search Started");
		List list = null;
		list = search(dto, 0, 0);
		log.debug("CollegeDaoHibImpl method search end");
		return list;
	}

	public List<CollegeDTO> search(CollegeDTO dto, int pageNo, int pageSize) throws DataBaseException {
		log.debug("CollegeDaoHibImpl method Search with Pagination Started");
		List list = null;
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(CollegeDTO.class);

		if (dto != null) {

			if (dto.getId() > 0) {

				criteria.add(Restrictions.eq("id", dto.getId()));
			}
			if (dto.getName() != null) {
				criteria.add(Restrictions.like("name", dto.getName() + "%"));
			}
			if (dto.getCity() != null) {
				criteria.add(Restrictions.like("city", dto.getCity() + "%"));
			}
		}
		// if page size is greater than zero the apply pagination
		if (pageSize > 0) {
			criteria.setFirstResult((pageNo - 1) * pageSize);
			criteria.setMaxResults(pageSize);
		}
		list = criteria.list();
		log.debug("CollegeDaoHibImpl method Search with Pagination end");
		return list;
	}

}
