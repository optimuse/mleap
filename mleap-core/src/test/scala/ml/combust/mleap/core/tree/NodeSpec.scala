package ml.combust.mleap.core.tree

import org.scalatest.FunSpec
import org.apache.spark.ml.linalg.Vectors

/**
  * Created by hwilkins on 1/21/16.
  */
class InternalNodeSpec extends FunSpec {
  describe("#typeName") {
    it("is InternalNode") {  }
  }

  describe("#predictImpl") {
    val leftNode = LeafNode(0.45)
    val rightNode = LeafNode(0.33)
    val features = Vectors.dense(Array(0.3))

    describe("when split goes left") {
      it("returns the left node") {
        val node = InternalNode(leftNode, rightNode, ContinuousSplit(0, 0.4))
        assert(node.predictImpl(features) == leftNode)
      }
    }

    describe("when split goes right") {
      it("returns the right node") {
        val node = InternalNode(leftNode, rightNode, ContinuousSplit(0, 0.2))
        assert(node.predictImpl(features) == rightNode)
      }
    }
  }
}

class LeafNodeSpec extends FunSpec {
  describe("#predictImpl") {
    it("returns itself") {
      val node = LeafNode(0.45)
      assert(node.predictImpl(Vectors.dense(Array(0.67))) == node)
    }
  }
}
