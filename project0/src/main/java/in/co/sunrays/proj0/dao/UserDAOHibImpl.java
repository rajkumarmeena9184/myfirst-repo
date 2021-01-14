package in.co.sunrays.proj0.dao;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Cache;
import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import in.co.sunrays.proj0.dto.UserDTO;
import in.co.sunrays.proj0.exception.DataBaseException;
import in.co.sunrays.proj0.exception.DuplicateRecordException;

/**
 * User Data Access Object provides Database CRUD operations. It is implemented
 * by plain Hibernate 3 API with Spring ORM.
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
@Repository(value = "userDao")
public class UserDAOHibImpl implements UserDAOInt {
	Logger log = Logger.getLogger(UserDAOHibImpl.class);
	@Autowired
	private SessionFactory sessionFactory = null;

	public long add(UserDTO dto) throws DuplicateRecordException {
		log.debug("UserDAOHibImpl method add Start");
		// TODO Auto-generated method stub
		long pk = 0;
		pk = (Long) sessionFactory.getCurrentSession().save(dto);
		log.debug("UserDAOHibImpl method add End");
		return pk;
	}

	public void update(UserDTO dto) throws DuplicateRecordException {
		log.debug("UserDAOHibImpl method Update Start");
		// TODO Auto-generated method stub
		sessionFactory.getCurrentSession().update(dto);
		log.debug("UserDAOHibImpl method Update End");
	}

	public void delete(long id) throws DataBaseException {
		log.debug("UserDAOHibImpl method Delete Start");
		// TODO Auto-generated method stub
		UserDTO dto = findByPK(id);
		sessionFactory.getCurrentSession().delete(dto);
		log.debug("UserDAOHibImpl method Delete End");
	}

	public UserDTO findByLogin(String login) throws DataBaseException {
		log.debug("UserDAOHibImpl method FindByLogin Start");
		// TODO Auto-generated method stub
		UserDTO dto = null;
		List list = sessionFactory.getCurrentSession().createCriteria(UserDTO.class)
				.add(Restrictions.eq("emailId", login)).list();
		if (list.size() == 1) {
			dto = (UserDTO) list.get(0);
		}
		log.debug("UserDAOHibImpl method FindByLogin End");
		return dto;
	}

	public UserDTO findByPK(long pk) throws DataBaseException {
		log.debug("UserDAOHibImpl method FindByPk Start");
		// TODO Auto-generated method stub
		UserDTO dto = null;
		dto = (UserDTO) sessionFactory.getCurrentSession().get(UserDTO.class, pk);
		log.debug("UserDAOHibImpl method FindByPk End");
		return dto;
	}

	public List search(UserDTO dto, int pageNo, int pageSize) {
		log.debug("UserDAOHibImpl method Search Start");
		// TODO Auto-generated method stub
		List list = null;
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(UserDTO.class);
		if (dto != null) {
			if (dto.getId() > 0) {
				criteria.add(Restrictions.eq("id", dto.getId()));
			}
			if (dto.getRoleId() > 0) {
				criteria.add(Restrictions.eq("roleId", dto.getRoleId()));
			}
			if (dto.getFirstName() != null) {
				criteria.add(Restrictions.like("firstName", dto.getFirstName() + "%"));
			}
			if (dto.getLastName() != null) {
				criteria.add(Restrictions.like("lastName", dto.getLastName() + "%"));
			}
		}
		// if page size is greater than zero the apply pagination
		if (pageSize > 0) {
			criteria.setFirstResult((pageNo - 1) * pageSize);
			criteria.setMaxResults(pageSize);
		}

		list = criteria.list();
		log.debug("UserDAOHibImpl method Search End");
		return list;
	}

	public List search(UserDTO dto) throws DataBaseException {
		// TODO Auto-generated method stub
		return search(dto, 0, 0);
	}

}
