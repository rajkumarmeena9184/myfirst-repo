package in.co.sunrays.proj0.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import in.co.sunrays.proj0.dto.CourseDTO;
import in.co.sunrays.proj0.exception.DataBaseException;
import in.co.sunrays.proj0.exception.DuplicateRecordException;

@Repository("courseDao")
public class CourseDAOHibImpl implements CourseDAOInt {
	@Autowired
	private SessionFactory sessionFactory = null;

	public long add(CourseDTO dto) {
		long pk = (Long) sessionFactory.getCurrentSession().save(dto);
		return pk;
	}

	public void update(CourseDTO dto) throws DuplicateRecordException {
		sessionFactory.getCurrentSession().update(dto);
	}

	public void delete(long id) throws DataBaseException {
		sessionFactory.getCurrentSession().delete(id);
	}

	public CourseDTO findByPk(long pk) {
		CourseDTO dto = null;
		dto = (CourseDTO) sessionFactory.getCurrentSession().get(CourseDTO.class, pk);
		return dto;
	}

	public CourseDTO findByName(String name) {
		CourseDTO dto = null;
		List list = sessionFactory.getCurrentSession().createCriteria(CourseDTO.class)
				.add(Restrictions.eq("name", name)).list();
		if (list.size() == 1) {
			dto = (CourseDTO) list.get(0);
		}
		return dto;
	}

	public List<CourseDTO> search(CourseDTO dto) throws DataBaseException {
		List list = null;
		list = search(dto, 0, 0);
		return list;
	}

	public List<CourseDTO> search(CourseDTO dto, int pageNo, int pageSize) throws DataBaseException {
		List list = null;
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(CourseDTO.class);
		if (dto != null) {
			if (dto.getId() > 0) {
				criteria.add(Restrictions.eq("name", dto.getId()));
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
		return list;
	}

}
