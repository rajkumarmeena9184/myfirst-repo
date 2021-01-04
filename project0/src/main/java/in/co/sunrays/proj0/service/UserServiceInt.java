package in.co.sunrays.proj0.service;

import java.util.List;

import in.co.sunrays.proj0.dto.UserDTO;
import in.co.sunrays.proj0.exception.ApplicatioException;
import in.co.sunrays.proj0.exception.DataBaseException;
import in.co.sunrays.proj0.exception.DuplicateRecordException;

public interface UserServiceInt {
	public long add(UserDTO dto) throws DuplicateRecordException;

	public void update(UserDTO dto) throws DuplicateRecordException;

	public void delete(long id) throws DataBaseException;

	public UserDTO findByLogin(String login) throws DataBaseException;

	public UserDTO findByPK(long pk) throws DataBaseException;

	public List search(UserDTO dto, int pageNo, int pageSize) throws DataBaseException;

	public List search(UserDTO dto) throws DataBaseException;

	public long registerUser(UserDTO dto) throws DuplicateRecordException, ApplicatioException;

	public boolean changePassword(long id, String oldPassword, String newPassword) throws DuplicateRecordException;

	public boolean forgetPassword(String login) throws ApplicatioException;

	public UserDTO authenticate(UserDTO dto) throws DataBaseException;
}
