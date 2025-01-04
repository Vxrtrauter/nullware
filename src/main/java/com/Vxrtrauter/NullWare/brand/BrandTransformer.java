package com.Vxrtrauter.NullWare.brand;

import net.minecraft.launchwrapper.IClassTransformer;
import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.tree.*;

public class BrandTransformer implements IClassTransformer {

    @Override
    public byte[] transform(String name, String transformedName, byte[] basicClass) {
        if (transformedName.equals("net.minecraft.client.ClientBrandRetriever")) {
            ClassNode classNode = new ClassNode();
            ClassReader classReader = new ClassReader(basicClass);
            classReader.accept(classNode, 0);

            for (MethodNode method : classNode.methods) {
                if (method.name.equals("getClientModName") || method.name.equals("func_74298_c")) {
                    InsnList insnList = new InsnList();
                    insnList.add(new MethodInsnNode(Opcodes.INVOKESTATIC, "com/Vxrtrauter/NullWare/BrandManager", "spoofBrand", "()Ljava/lang/String;", false));
                    insnList.add(new InsnNode(Opcodes.ARETURN));
                    method.instructions.clear();
                    method.instructions.add(insnList);
                }
            }

            ClassWriter classWriter = new ClassWriter(ClassWriter.COMPUTE_MAXS | ClassWriter.COMPUTE_FRAMES);
            classNode.accept(classWriter);
            return classWriter.toByteArray();
        }
        return basicClass;
    }
}
