#Classe que representa dos tipos de atributos(Ex: Para o atributo Outlook, temos os tipos: sunny, overcast, rainy)
class TypeData:
    def __init__(self,type_name, n_times,entropy, is_homogeneity):
        self.type_name = type_name
        self.n_times = n_times
        self.entropy = entropy
        self.is_homogeneity = is_homogeneity
        self.class_list = []
        self.flag = False

    def __repr__(self):
        return str(self.type_name)
    
    def __eq__(self, other):
        return self.type_name == other.type_name  
    
#Classe que representa os atributos
class AttributeData:
    def __init__(self, attribute_name, attribute_entropy):    
        self.attribute_name = attribute_name
        self.attribute_entropy = attribute_entropy   
        self.type_list = []
        self.flag = False
        self.is_node = False
        self.total = 0
        self.count = 0
        
    def __repr__(self):  
        return str(self.attribute_name)

#Classe que representa os nos da árvore
class Node:
    def __init__(self, key, decision):
        self.key = key
        self.childs = []
        self.is_leaf = False
        self.node_homo_list = []
        self.decision = decision
        self.decision_list = []
        self.is_root = False
        self.root_child = False
        self.branches = []
 
    def __repr__(self):
        return str(self.key) + " " + str(self.decision_list)

    def __eq__(self, other):
        if other == None:
            #print(other)
            return other == None
        elif isinstance(other, str):
            return self.key == other    
        elif isinstance(other, AttributeData):    
            return self.key == other
        elif isinstance(other, Class):
            return self.key == other
        elif isinstance(other, Node):
            return self.key == other.key    

    def __hash__(self):
        return hash(self.key)   

#Classe que une a class_list a ocurrencia por cada classe
class Class:
    def __init__(self, class_name, class_ocurrence):
        self.class_name = class_name
        self.class_ocurrence = class_ocurrence
        self.list = []

    def __repr__(self):
        return str(self.class_name) + " " + str(self.class_ocurrence)
    
    def __eq__(self, other):
        if other is None:
            return self.class_name is None
        elif isinstance(other, Class):    
            return self.class_name == other.class_name

    def __hash__(self):
        return hash(self.class_name)    

