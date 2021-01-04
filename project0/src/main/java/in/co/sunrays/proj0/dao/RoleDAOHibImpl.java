package in.co.sunrays.proj0.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import in.co.sunrays.proj0.dto.RoleDTO;
import in.co.sunrays.proj0.exception.DataBaseException;
import in.co.sunrays.proj0.exception.DuplicateRecordException;

@Repository("roleDao")
public class RoleDAOHibImpl implements RoleDAOInt {
	@Autowired
	private SessionFactory sessionFactory = null;

	public long add(RoleDTO dto) throws DuplicateRecordException {
		long pk = 0;
		pk = (Long) sessionFactory.getCurrentSession().save(dto);
		return pk;
	}

	public void update(RoleDTO dto) throws DuplicateRecordException {
		sessionFactory.getCurrentSession().update(dto);
	}

	public void delete(long id) throws DataBaseException {
		sessionFactory.getCurrentSession().delete(id);
	}

	public RoleDTO findByPk(long pk) throws DataBaseException {
		RoleDTO dto = null;
		dto = (RoleDTO) sessionFactory.getCurrentSession().get(RoleDTO.class, pk);
		return dto;
	}

	public RoleDTO findByName(String name) throws DataBaseException {
		RoleDTO dto = null;
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(RoleDTO.class);
		List list = criteria.add(Restrictions.eq("roleName", name)).list();
		if (list.size() == 1) {
			dto = (RoleDTO) list.get(0);
		}
		return dto;
	}

	public RoleDTO findByLogin(String login) throws DataBaseException {
		// TODO Auto-generated method stub
		return null;
	}

	public List list() throws DataBaseException {
		List list = null;
		list = sessionFactory.getCurrentSession().createCriteria(RoleDTO.class).list();
		return list;
	}

	public List<RoleDTO> search(RoleDTO dto, int pageNo, int pageSize) throws DataBaseException {
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
		return list;
	}

	public List<RoleDTO> search(RoleDTO dto) throws DataBaseException {
		// TODO Auto-generated method stub
		return null;
	}

}
