package in.co.sunrays.proj0.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import in.co.sunrays.proj0.dto.MarksheetDTO;
import in.co.sunrays.proj0.exception.DataBaseException;
import in.co.sunrays.proj0.exception.DuplicateRecordException;

@Repository("marksheetDao")
public class MarksheetDAOHibImpl implements MarksheetDAOInt {
	@Autowired
	private SessionFactory sessionFactory = null;

	public long add(MarksheetDTO dto) throws DuplicateRecordException {
		long pk = 0;
		pk = (Long) sessionFactory.getCurrentSession().save(dto);
		return pk;
	}

	public void update(MarksheetDTO dto) throws DuplicateRecordException {
		sessionFactory.getCurrentSession().update(dto);
	}

	public void delete(long id) throws DataBaseException {
		sessionFactory.getCurrentSession().delete(id);
	}

	public MarksheetDTO findByPk(long pk) throws DataBaseException {
		MarksheetDTO dto = null;
		dto = (MarksheetDTO) sessionFactory.getCurrentSession().get(MarksheetDTO.class, pk);
		return dto;
	}

	public MarksheetDTO findByLogin(String login) throws DataBaseException {
		// TODO Auto-generated method stub
		return null;
	}

	public MarksheetDTO findByRollNumber(String rollNo) throws DataBaseException {
		MarksheetDTO dto = null;
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(MarksheetDTO.class);
		criteria.add(Restrictions.eq("rollnumber", rollNo));
		List list = criteria.list();
		if (list.size() == 1) {
			dto = (MarksheetDTO) list.get(0);
		}
		return dto;
	}

	public List<MarksheetDTO> search(MarksheetDTO dto) throws DataBaseException {
		List list = search(dto, 0, 0);
		return list;
	}

	public List<MarksheetDTO> search(MarksheetDTO dto, int pageNo, int pageSize) throws DataBaseException {
		List list = null;
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(MarksheetDTO.class);
		if (dto != null) {
			if (dto.getId() > 0) {
				criteria.add(Restrictions.eq("id", dto.getId()));
			}
			if (dto.getStudentId() > 0) {
				criteria.add(Restrictions.eq("studentId", dto.getStudentId()));
			}
			if (dto.getStudentName() != null) {
				criteria.add(Restrictions.eq("studentId", dto.getStudentName()));
			}
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
		return list;
	}

}
