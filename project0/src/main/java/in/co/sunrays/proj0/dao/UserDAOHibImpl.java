package in.co.sunrays.proj0.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import in.co.sunrays.proj0.dto.UserDTO;
import in.co.sunrays.proj0.exception.DataBaseException;
import in.co.sunrays.proj0.exception.DuplicateRecordException;

@Repository(value = "userDao")
public class UserDAOHibImpl implements UserDAOInt {
	@Autowired
	private SessionFactory sessionFactory = null;

	public long add(UserDTO dto) throws DuplicateRecordException {
		// TODO Auto-generated method stub
		long pk = 0;
		pk = (Long) sessionFactory.getCurrentSession().save(dto);
		return pk;
	}

	public void update(UserDTO dto) throws DuplicateRecordException {
		// TODO Auto-generated method stub
		sessionFactory.getCurrentSession().update(dto);

	}

	public void delete(long id) throws DataBaseException {
		// TODO Auto-generated method stub
		UserDTO d = new UserDTO();
		d.setId(id);
		sessionFactory.getCurrentSession().delete(d);

	}

	public UserDTO findByLogin(String login) throws DataBaseException {
		// TODO Auto-generated method stub
		UserDTO dto = null;
		List list = sessionFactory.getCurrentSession().createCriteria(UserDTO.class)
				.add(Restrictions.eq("emailId", login)).list();
		if (list.size() == 1) {
			dto = (UserDTO) list.get(0);
		}
		return dto;
	}

	public UserDTO findByPK(long pk) throws DataBaseException {
		// TODO Auto-generated method stub
		UserDTO dto = null;
		dto = (UserDTO) sessionFactory.getCurrentSession().get(UserDTO.class, pk);
		return dto;
	}

	public List search(UserDTO dto, int pageNo, int pageSize) {
		// TODO Auto-generated method stub
		List list = null;
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(UserDTO.class);
		list = criteria.list();
		return list;
	}

	public List search(UserDTO dto) throws DataBaseException {
		// TODO Auto-generated method stub
		return search(dto, 0, 0);
	}

}
