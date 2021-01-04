package in.co.sunrays.proj0.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import in.co.sunrays.proj0.dto.FacultyDTO;
import in.co.sunrays.proj0.exception.DataBaseException;
import in.co.sunrays.proj0.exception.DuplicateRecordException;

@Repository("facultyDao")
public class FacultyDAOHibImpl implements FacultyDAOInt {
	@Autowired
	private SessionFactory sessionFactory = null;

	public long add(FacultyDTO dto) throws DuplicateRecordException {
		long pk = 0;
		pk = (Long) sessionFactory.getCurrentSession().save(dto);
		return pk;
	}

	public void update(FacultyDTO dto) throws DuplicateRecordException {
		sessionFactory.getCurrentSession().update(dto);

	}

	public void delete(long id) throws DataBaseException {
		sessionFactory.getCurrentSession().delete(id);
	}

	public FacultyDTO findByPk(long pk) throws DataBaseException {
		FacultyDTO dto = null;
		dto = (FacultyDTO) sessionFactory.getCurrentSession().get(FacultyDTO.class, pk);
		return dto;
	}

	public FacultyDTO findByLogin(String login) throws DataBaseException {
		FacultyDTO dto = null;
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(FacultyDTO.class);
		criteria.add(Restrictions.like("emailID", login));
		List list = criteria.list();
		if (list.size() == 1) {
			dto = (FacultyDTO) list.get(0);
		}
		return dto;
	}

	public List<FacultyDTO> search(FacultyDTO dto) throws DataBaseException {
		List list = null;
		list = search(dto, 0, 0);
		return list;
	}

	public List<FacultyDTO> search(FacultyDTO dto, int pageNo, int pageSize) throws DataBaseException {

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
		return list;
	}

}
