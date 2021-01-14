package in.co.sunrays.proj0.dao;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import in.co.sunrays.proj0.dto.MarksheetDTO;
import in.co.sunrays.proj0.exception.DataBaseException;
import in.co.sunrays.proj0.exception.DuplicateRecordException;

/**
 * Marksheet Data Access Object provides Database CRUD operations. It is
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
@Repository("marksheetDao")
public class MarksheetDAOHibImpl implements MarksheetDAOInt {
	Logger log = Logger.getLogger(MarksheetDAOHibImpl.class);
	@Autowired
	private SessionFactory sessionFactory = null;

	public long add(MarksheetDTO dto) throws DuplicateRecordException {
		log.debug("MarksheetDAOHibImpl method Add Start");
		long pk = 0;
		pk = (Long) sessionFactory.getCurrentSession().save(dto);
		log.debug("MarksheetDAOHibImpl method Add End");
		return pk;
	}

	public void update(MarksheetDTO dto) throws DuplicateRecordException {
		log.debug("MarksheetDAOHibImpl method Update Start");
		sessionFactory.getCurrentSession().update(dto);
		log.debug("MarksheetDAOHibImpl method Update End");
	}

	public void delete(long id) throws DataBaseException {
		log.debug("MarksheetDAOHibImpl method Delete Start");
		MarksheetDTO dto = findByPk(id);
		sessionFactory.getCurrentSession().delete(dto);
		log.debug("MarksheetDAOHibImpl method Delete End");
	}

	public MarksheetDTO findByPk(long pk) throws DataBaseException {
		log.debug("MarksheetDAOHibImpl method FindByPk Start");
		MarksheetDTO dto = null;
		dto = (MarksheetDTO) sessionFactory.getCurrentSession().get(MarksheetDTO.class, pk);
		log.debug("MarksheetDAOHibImpl method FindByPk End");
		return dto;
	}

	public MarksheetDTO findByLogin(String login) throws DataBaseException {
		// TODO Auto-generated method stub
		return null;
	}

	public MarksheetDTO findByRollNumber(String rollNo) throws DataBaseException {
		log.debug("MarksheetDAOHibImpl method FindByRollNumber Start");
		MarksheetDTO dto = null;
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(MarksheetDTO.class);
		criteria.add(Restrictions.eq("rollnumber", rollNo));
		List list = criteria.list();
		if (list.size() == 1) {
			dto = (MarksheetDTO) list.get(0);
		}
		log.debug("MarksheetDAOHibImpl method FindByRollNumber End");
		return dto;
	}

	public List<MarksheetDTO> search(MarksheetDTO dto) throws DataBaseException {
		log.debug("MarksheetDAOHibImpl method Search Start");
		List list = search(dto, 0, 0);
		log.debug("MarksheetDAOHibImpl method Search End");
		return list;
	}

	public List<MarksheetDTO> search(MarksheetDTO dto, int pageNo, int pageSize) throws DataBaseException {
		log.debug("MarksheetDAOHibImpl method Search With Pagination Start");
		List list = null;
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(MarksheetDTO.class);
		if (dto != null) {
			if (dto.getId() > 0) {
				criteria.add(Restrictions.eq("id", dto.getId()));
			}
			if (dto.getStudentId() > 0) {
				System.out.println("St_ID=" + dto.getStudentId());
				criteria.add(Restrictions.eq("studentId", dto.getStudentId()));
			}
			/*
			 * if (dto.getStudentName() != null) { criteria.add(Restrictions.eq("studentId",
			 * dto.getStudentName())); }
			 */
			if (dto.getRollnumber() != null) {
				criteria.add(Restrictions.eq("rollnumber", dto.getRollnumber()));
			}
		}
		// if page size is greater than zero the apply pagination
		if (pageSize > 0) {
			criteria.setFirstResult((pageNo - 1) * pageSize);
			criteria.setMaxResults(pageSize);
		}
		list = criteria.list();
		log.debug("MarksheetDAOHibImpl method Search With Pagination End");
		return list;
	}

	public List meritList(int pageNo, int pageSize) throws DataAccessException {
		log.debug("MarksheetDAOHibImpl method MeritList Start");
		// TODO Auto-generated method stub
		List list = null;
		StringBuffer hql = new StringBuffer(
				"from MarksheetDTO where  physics >32 and chemistry >32 and maths >32 order by (physics + chemistry + maths) desc");
		Query query = sessionFactory.getCurrentSession().createQuery(hql.toString());
		// if page size is greater than zero then apply pagination
		if (pageSize > 0) {
			pageNo = ((pageNo - 1) * pageSize);
			query.setFirstResult(pageNo);
			query.setMaxResults(pageSize);
		}
		list = query.list();
		log.debug("MarksheetDAOHibImpl method MeritList End");
		return list;
	}

}
