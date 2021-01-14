package in.co.sunrays.proj0.dao;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import in.co.sunrays.proj0.dto.RoleDTO;
import in.co.sunrays.proj0.exception.DataBaseException;
import in.co.sunrays.proj0.exception.DuplicateRecordException;

/**
 * Role Data Access Object provides Database CRUD operations. It is
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
@Repository("roleDao")
public class RoleDAOHibImpl implements RoleDAOInt {
	Logger log = Logger.getLogger(MarksheetDAOHibImpl.class);
	@Autowired
	private SessionFactory sessionFactory = null;

	public long add(RoleDTO dto) throws DuplicateRecordException {
		log.debug("RoleDAOHibImpl method Add Start");
		long pk = 0;
		pk = (Long) sessionFactory.getCurrentSession().save(dto);
		log.debug("RoleDAOHibImpl method Add End");
		return pk;
	}

	public void update(RoleDTO dto) throws DuplicateRecordException {
		log.debug("RoleDAOHibImpl method Update Start");
		sessionFactory.getCurrentSession().update(dto);
		log.debug("RoleDAOHibImpl method Update End");
	}

	public void delete(long id) throws DataBaseException {
		log.debug("RoleDAOHibImpl method Delete Start");
		RoleDTO dto = findByPk(id);
		sessionFactory.getCurrentSession().delete(dto);
		log.debug("RoleDAOHibImpl method Delete End");
	}

	public RoleDTO findByPk(long pk) throws DataBaseException {
		log.debug("RoleDAOHibImpl method FindByPk Start");
		RoleDTO dto = null;
		dto = (RoleDTO) sessionFactory.getCurrentSession().get(RoleDTO.class, pk);
		log.debug("RoleDAOHibImpl method FindByPk End");
		return dto;
	}

	public RoleDTO findByName(String name) throws DataBaseException {
		log.debug("RoleDAOHibImpl method FindByName Start");
		RoleDTO dto = null;
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(RoleDTO.class);
		List list = criteria.add(Restrictions.eq("roleName", name)).list();
		if (list.size() == 1) {
			dto = (RoleDTO) list.get(0);
		}
		log.debug("RoleDAOHibImpl method FindByName End");
		return dto;
	}

	public RoleDTO findByLogin(String login) throws DataBaseException {
		// TODO Auto-generated method stub
		return null;
	}

	public List list() throws DataBaseException {
		log.debug("RoleDAOHibImpl method List Start");
		List list = null;
		list = sessionFactory.getCurrentSession().createCriteria(RoleDTO.class).list();
		log.debug("RoleDAOHibImpl method List End");
		return list;
	}

	public List<RoleDTO> search(RoleDTO dto, int pageNo, int pageSize) throws DataBaseException {
		log.debug("RoleDAOHibImpl method Search Start");
		List list = null;
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(RoleDTO.class);
		if (dto != null) {
			if (dto.getId() > 0) {
				criteria.add(Restrictions.eq("id", dto.getId()));
			}
			if (dto.getRoleName() != null) {
				criteria.add(Restrictions.like("roleName", dto.getRoleName() + "%"));
			}
			if (dto.getDescription() != null) {
				criteria.add(Restrictions.like("description", dto.getDescription() + "%"));
			}
		}
		// if page Size greater then zero then pagination apply
		if (pageSize > 0) {
			criteria.setFirstResult((pageNo - 1) * pageSize);
			criteria.setMaxResults(pageSize);
		}
		list = criteria.list();
		log.debug("RoleDAOHibImpl method Search End");
		return list;
	}

	public List<RoleDTO> search(RoleDTO dto) throws DataBaseException {
		// TODO Auto-generated method stub
		return null;
	}

}
