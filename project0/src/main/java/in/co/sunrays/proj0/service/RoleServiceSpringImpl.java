package in.co.sunrays.proj0.service;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import in.co.sunrays.proj0.dao.RoleDAOInt;
import in.co.sunrays.proj0.dto.RoleDTO;
import in.co.sunrays.proj0.exception.DataBaseException;
import in.co.sunrays.proj0.exception.DuplicateRecordException;
/**
 * Session facade of Role Service. It is transactional, apply delcarative
 * transactions with help of Spring AOP.
 *
 * If unchecked exception is propagated from a method then transaction will be
 * rolled back.
 *
 * Default propogation value is Propagation.REQUIRED and readOnly = false
 *
 * @author SunilOS
 * @version 1.0
 * @Copyright (c) SunilOS
 */
@Service("roleService")
public class RoleServiceSpringImpl implements RoleServiceInt {
	Logger log = Logger.getLogger(RoleServiceSpringImpl.class);
	@Autowired
	private RoleDAOInt roleDao = null;
	/**
	 * Adds a Role
	 *
	 * @param dto
	 * @throws ApplicationException
	 * @throws DuplicateRecordException
	 */
	@Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
	public long add(RoleDTO dto) throws DuplicateRecordException {
		log.debug("RoleServiceSpringImpl method add Start");
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
		log.debug("RoleServiceSpringImpl method add End");
		return pk;
	}
	/**
	 * Update a Role
	 *
	 * @param dto
	 * @throws ApplicationException
	 * @throws DuplicateRecordException
	 */
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void update(RoleDTO dto) throws DuplicateRecordException {
		log.debug("RoleServiceSpringImpl method Update Start");
		roleDao.update(dto);
		log.debug("RoleServiceSpringImpl method Update End");
	}
	/**
	 * Delete a Role
	 *
	 * @param Id-long
	 * @throws ApplicationException
	 * @throws DataBaseException
	 */
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void delete(long id) throws DataBaseException {
		log.debug("RoleServiceSpringImpl method Delete Start");
		roleDao.delete(id);
		log.debug("RoleServiceSpringImpl method Delete end");
	}
	/**
	 * findByName a Role
	 *
	 * @param name
	 * @throws ApplicationException
	 * @throws DataBaseException
	 */
	@Transactional(readOnly = true)
	public RoleDTO findByName(String name) throws DataBaseException {
		log.debug("RoleServiceSpringImpl method findByName Start");
		RoleDTO dto = null;
		dto = roleDao.findByName(name);
		log.debug("RoleServiceSpringImpl method findByName end");
		return dto;
	}

	public RoleDTO findByLogin(String login) throws DataBaseException {
		// TODO Auto-generated method stub
		return null;
	}
	/**
	 * findByPk a Role
	 *
	 * @param pk
	 * @throws ApplicationException
	 * @throws DataBaseException
	 */
	@Transactional(readOnly = true)
	public RoleDTO findByPK(long pk) throws DataBaseException {
		log.debug("RoleServiceSpringImpl method findByPk Start");
		// TODO Auto-generated method stub
		RoleDTO dto = null;
		dto = roleDao.findByPk(pk);
		log.debug("RoleServiceSpringImpl method findByName Start");
		return dto;
	}
	/**
	 * Search a Role
	 *
	 * @param Dto
	 * @param pageNo
	 * @param pageSize
	 * @throws ApplicationException
	 * @throws DataBaseException
	 */
	@Transactional(readOnly = true)
	public List search(RoleDTO dto, int pageNo, int pageSize) throws DataBaseException {
		log.debug("RoleServiceSpringImpl method Search with Paginaton Start");
		List list = null;
		list = roleDao.search(dto, pageNo, pageSize);
		log.debug("RoleServiceSpringImpl method Search with Paginaton End");
		return list;
	}
	/**
	 * Search a Role
	 *
	 * @param Dto
	 * @throws ApplicationException
	 * @throws DataBaseException
	 */
	@Transactional(readOnly = true)
	public List search(RoleDTO dto) throws DataBaseException {
		log.debug("RoleServiceSpringImpl method Search Start");
		List list = null;
		list = roleDao.search(dto);
		log.debug("RoleServiceSpringImpl method Search End");
		return list;
	}
	/**
	 * List a Role
	 *
	 * @param Dto
	 * @throws ApplicationException
	 * @throws DataBaseException
	 */
	@Transactional(readOnly = true)
	public List list() throws DataBaseException {
		log.debug("RoleServiceSpringImpl method List Start");
		List list = null;
		list = roleDao.list();
		log.debug("RoleServiceSpringImpl method List End");
		return list;
	}

}
