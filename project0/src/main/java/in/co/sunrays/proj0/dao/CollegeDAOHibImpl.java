package in.co.sunrays.proj0.dao;

import java.util.List;

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
	@Autowired
	private SessionFactory sessionFactory = null;

	public long add(CollegeDTO dto) throws DuplicateRecordException {
		long pk = (Long) sessionFactory.getCurrentSession().save(dto);
		return pk;
	}

	public void update(CollegeDTO dto) throws DuplicateRecordException {

		sessionFactory.getCurrentSession().update(dto);
	}

	public void delete(long id) throws DataBaseException {

		sessionFactory.getCurrentSession().delete(id);
	}

	public CollegeDTO findByPk(long pk) throws DataBaseException {
		CollegeDTO dto = null;
		dto = (CollegeDTO) sessionFactory.getCurrentSession().get(CollegeDTO.class, pk);
		return dto;
	}

	public CollegeDTO findByName(String name) throws DataBaseException {
		CollegeDTO dto = null;
		List<CollegeDTO> list = sessionFactory.getCurrentSession().createCriteria(CollegeDTO.class)
				.add(Restrictions.eq("name", name)).list();
		if (list.size() == 1) {
			dto = (CollegeDTO) list.get(0);
		}
		return dto;
	}

	public List<CollegeDTO> search(CollegeDTO dto) throws DataBaseException {
		List list = null;
		list = search(dto, 0, 0);
		return list;
	}

	public List<CollegeDTO> search(CollegeDTO dto, int pageNo, int pageSize) throws DataBaseException {
		List list = null;
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(CollegeDTO.class);

		if (dto != null) {

			if (dto.getId() > 0) {

				criteria.add(Restrictions.eq("id", dto.getId()));
			}
			if (dto.getName() != null) {
				criteria.add(Restrictions.like("name", dto.getName() + "%"));
			}
		}
		// if page size is greater than zero the apply pagination
		if (pageSize > 0) {
			criteria.setFirstResult((pageNo - 1) * pageSize);
			criteria.setMaxResults(pageSize);
		}
		list = criteria.list();
		return list;
	}

}
