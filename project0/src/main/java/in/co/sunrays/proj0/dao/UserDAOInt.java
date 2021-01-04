package in.co.sunrays.proj0.dao;

import java.util.List;

import in.co.sunrays.proj0.dto.UserDTO;
import in.co.sunrays.proj0.exception.DataBaseException;
import in.co.sunrays.proj0.exception.DuplicateRecordException;
import in.co.sunrays.proj0.exception.RecordNotFoundException;

public interface UserDAOInt {

	public long add(UserDTO dto) throws DuplicateRecordException;

	public void update(UserDTO dto) throws DuplicateRecordException;

	public void delete(long id) throws DataBaseException;

	public UserDTO findByLogin(String login) throws DataBaseException;

	public UserDTO findByPK(long pk) throws DataBaseException;

	public List search(UserDTO dto, int pageNo, int pageSize);

	public List search(UserDTO dto) throws DataBaseException;

}
