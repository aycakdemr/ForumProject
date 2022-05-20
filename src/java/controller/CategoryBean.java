package controller;

import jakarta.faces.application.FacesMessage;
import jakarta.faces.component.UIComponent;
import jakarta.faces.context.FacesContext;
import jakarta.faces.validator.ValidatorException;
import dao.CategoryDao;
import entity.Category;
import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Named;
import java.io.Serializable;
import java.util.List;

/**
 *
 * @author CASPER
 */
@Named(value="categoryBean")
@SessionScoped
public class CategoryBean implements Serializable{

    private Category entity;
    private CategoryDao dao;
    private List<Category> list;
    public CategoryBean() {
    }
    public void create(){
        this.getDao().create(entity);
        entity=new Category();
    }
    public void update(){
        this.getDao().update(entity);
        entity=new Category();
    }
    public void delete(Category c){
        this.getDao().delete(c);
        entity=new Category();
    }
    public void clear(){
        entity=new Category();
    }
    public boolean validate(FacesContext context,UIComponent cmp,Object value)throws ValidatorException{
        String v=(String) value; 
        if(v.isEmpty()){
            throw new ValidatorException(new FacesMessage("Kategori Adı Alanı Boş Olamaz!"));
        }
        return true;
    }
    public String getName(int id){
        Category c=this.getDao().findById(id);
        return c.getName();
    }

     public Category getEntity() {
        if(entity == null) {
            entity = new Category();
        }
        return entity;
    }

     public Category findById(){
        return this.getDao().findById(entity.getId());
    }
    public void setEntity(Category entity) {
        this.entity = entity;
    }

    public CategoryDao getDao() {
        if(dao == null) {
            dao = new CategoryDao();
        }
        return dao;
    }

    public void setDao(CategoryDao dao) {
        this.dao = dao;
    }

    public List<Category> getList() {
        this.list = this.getDao().getList();
        return list;
    }

    public void setList(List<Category> list) {
        this.list = list;
    }
    
    
}

