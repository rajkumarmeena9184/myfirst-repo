package in.co.sunrays.proj0.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import in.co.sunrays.proj0.dao.RoleDAOInt;
import in.co.sunrays.proj0.dto.RoleDTO;
import in.co.sunrays.proj0.exception.DataBaseException;
import in.co.sunrays.proj0.exception.DuplicateRecordException;

@Service("roleService")
public class RoleServiceSpringImpl implements RoleServiceInt {
	@Autowired
	private RoleDAOInt roleDao = null;

	@Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
	public long add(RoleDTO dto) throws DuplicateRecordException {
		long pk = 0;

		try {
			RoleDTO existDTO = roleDao.findByName(dto.getRoleName());

			if (existDTO != null) {
				throw new DuplicateRecordException("RoleName Already Exist");
			}
		} catch (DataBaseException e) { // TODO Auto-generated catch block
			e.printStackTrace();
		}

		pk = roleDao.add(dto);
		return pk;
	}

	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void update(RoleDTO dto) throws DuplicateRecordException {

		roleDao.update(dto);
	}

	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void delete(long id) throws DataBaseException {

		roleDao.delete(id);
	}

	@Transactional(readOnly = true)
	public RoleDTO findByName(String name) throws DataBaseException {
		RoleDTO dto = null;
		dto = roleDao.findByName(name);
		return dto;
	}

	public RoleDTO findByLogin(String login) throws DataBaseException {
		// TODO Auto-generated method stub
		return null;
	}

	@Transactional(readOnly = true)
	public RoleDTO findByPK(long pk) throws DataBaseException {
		// TODO Auto-generated method stub
		RoleDTO dto = null;
		dto = roleDao.findByPk(pk);
		return dto;
	}

	@Transactional(readOnly = true)
	public List search(RoleDTO dto, int pageNo, int pageSize) throws DataBaseException {
		List list = null;
		list = roleDao.search(dto, pageNo, pageSize);
		return list;
	}

	@Transactional(readOnly = true)
	public List search(RoleDTO dto) throws DataBaseException {

		List list = null;
		list = roleDao.search(dto);
		return list;
	}

	@Transactional(readOnly = true)
	public List list() throws DataBaseException {
		List list = null;
		list = roleDao.list();
		return list;
	}

}
