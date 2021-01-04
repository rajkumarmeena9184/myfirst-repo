package in.co.sunrays.proj0.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import in.co.sunrays.proj0.dto.StudentDTO;
import in.co.sunrays.proj0.exception.DataBaseException;
import in.co.sunrays.proj0.exception.DuplicateRecordException;

@Repository("studentDao")
public class StudentDAOHibImpl implements StudentDAOInt {
	@Autowired
	private SessionFactory sessionFactory = null;

	public long add(StudentDTO dto) throws DuplicateRecordException {
		long pk = 0;
		pk = (Long) sessionFactory.getCurrentSession().save(dto);
		return pk;
	}

	public void update(StudentDTO dto) throws DuplicateRecordException {
		sessionFactory.getCurrentSession().update(dto);
	}

	public void delete(long id) throws DataBaseException {
		sessionFactory.getCurrentSession().delete(id);
	}

	public StudentDTO findByPk(long pk) throws DataBaseException {
		StudentDTO dto = null;
		dto = (StudentDTO) sessionFactory.getCurrentSession().get(StudentDTO.class, pk);
		return dto;
	}

	public StudentDTO findByLogin(String login) throws DataBaseException {
		StudentDTO dto = null;
		List list = sessionFactory.getCurrentSession().createCriteria(StudentDTO.class)
				.add(Restrictions.eq("emailId", login)).list();
		if (list.size() == 1) {
			dto = (StudentDTO) list.get(0);
		}
		return dto;
	}

	public List<StudentDTO> search(StudentDTO dto) throws DataBaseException {
		List list = null;
		list = search(dto, 0, 0);
		return list;
	}

	public List<StudentDTO> search(StudentDTO dto, int pageNo, int pageSize) throws DataBaseException {
		List list = null;
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(StudentDTO.class);
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
		return list;
	}

}
