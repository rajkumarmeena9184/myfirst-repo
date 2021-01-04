package in.co.sunrays.proj0.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import in.co.sunrays.proj0.dto.SubjectDTO;
import in.co.sunrays.proj0.exception.DataBaseException;
import in.co.sunrays.proj0.exception.DuplicateRecordException;

@Repository("subjectDao")
public class SubjectDAOHibImpl implements SubjectDAOInt {
	@Autowired
	private SessionFactory sessionFactory = null;

	public long add(SubjectDTO dto) throws DuplicateRecordException {
		long pk = 0;
		pk = (Long) sessionFactory.getCurrentSession().save(dto);
		return pk;
	}

	public void update(SubjectDTO dto) throws DuplicateRecordException {
		sessionFactory.getCurrentSession().update(dto);
	}

	public void delete(long id) throws DataBaseException {
		sessionFactory.getCurrentSession().delete(id);
	}

	public SubjectDTO findByPk(long pk) throws DataBaseException {
		SubjectDTO dto = null;
		dto = (SubjectDTO) sessionFactory.getCurrentSession().get(SubjectDTO.class, pk);
		return dto;
	}

	public SubjectDTO findByLogin(String login) throws DataBaseException {
		return null;
	}

	public SubjectDTO findByName(String name) throws DataBaseException {
		SubjectDTO dto = null;
		List list = sessionFactory.getCurrentSession().createCriteria(SubjectDTO.class)
				.add(Restrictions.eq("subjectName", name)).list();
		if (list.size() == 1) {
			dto = (SubjectDTO) list.get(0);
		}
		return dto;
	}

	public List<SubjectDTO> search(SubjectDTO dto) throws DataBaseException {
		List list = null;
		list = search(dto, 0, 0);
		return list;
	}

	public List<SubjectDTO> search(SubjectDTO dto, int pageNo, int pageSize) throws DataBaseException {
		List list = null;
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(SubjectDTO.class);
		if (dto != null) {
			if (dto.getId() > 0) {
				criteria.add(Restrictions.eq("id", dto.getId()));
			}
			if (dto.getSubjectName() != null) {
				criteria.add(Restrictions.like("subjectName", dto.getSubjectName() + "%"));
			}
			if (dto.getSemester() != null) {
				criteria.add(Restrictions.like("semester", dto.getSemester() + "%"));
			}
			if (dto.getDescription() != null) {
				criteria.add(Restrictions.like("description", dto.getDescription() + "%"));
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
