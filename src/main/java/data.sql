INSERT INTO CATEGORY(cat_id,cat_name) VALUES (1, 'Tissue');
INSERT INTO ITEM(item_id,name,quantity,cat_id) VALUES (2, 'Animal Tissue', '2', 1);
INSERT INTO EXPERIMENT(expmnt_id,expmnt_name,expmnt_phase,expmnt_description,item_id) VALUES (3, 'Cell Culture', 'Initial', 'Testing animal tissue', 2);